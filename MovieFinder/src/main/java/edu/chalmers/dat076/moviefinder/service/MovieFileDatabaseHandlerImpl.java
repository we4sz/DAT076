/* 
 * The MIT License
 *
 * Copyright 2014 Anton, Carl, John, Peter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import edu.chalmers.dat076.moviefinder.model.TraktShowResponse;
import edu.chalmers.dat076.moviefinder.persistence.Episode;
import edu.chalmers.dat076.moviefinder.persistence.EpisodeRepository;
import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import edu.chalmers.dat076.moviefinder.persistence.Series;
import edu.chalmers.dat076.moviefinder.persistence.SeriesRepository;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A service for saving and removing files from the database. New files are
 * looked up via a third party media API to try and get information about them.
 *
 * @author Peter
 */
@Service
public class MovieFileDatabaseHandlerImpl implements MovieFileDatabaseHandler {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    private final Semaphore lock = new Semaphore(300);

    private final HashMap<String, Semaphore> serieLock = new HashMap<>();

    private final Semaphore movieSemaphore = new Semaphore(1, true);

    private final Semaphore episodeSemaphore = new Semaphore(1, true);

    @Override
    public void saveFile(final Path path) {
        Runnable r = new Runnable() {

            @Override
            @Transactional
            public void run() {

                TemporaryMedia temporaryMedia = new TitleParser().parseMedia(path.getFileName().toString());
                TraktResponse traktData = new TraktHandler().getByTmpMedia(temporaryMedia);
                if (traktData != null) {
                    if (traktData instanceof TraktMovieResponse) {
                        Movie movie = new Movie(path.toString(), traktData);
                        try {
                            try {
                                movieSemaphore.acquire();
                            } catch (InterruptedException ex) {
                            }
                            movieRepository.save(movie);
                            movieSemaphore.release();
                        } catch (DataIntegrityViolationException e) {
                        }
                    } else {
                        TraktEpisodeResponse epr = (TraktEpisodeResponse) traktData;
                        System.out.println("Hämtar: " + epr.getEpisode().getSeason() + "/" + epr.getEpisode().getNumber() + " - " + epr.getShow().getTitle());
                        Series s;
                        Semaphore sLock = serieLock.get(epr.getShow().getTitle());
                        if (sLock == null) {
                            System.out.println("Hämtar: " + epr.getShow().getTitle());
                            sLock = new Semaphore(1, true);
                            serieLock.put(epr.getShow().getTitle(), sLock);
                        }
                        try {
                            sLock.acquire();
                        } catch (InterruptedException ex) {
                        }

                        s = seriesRepository.findByImdbId(epr.getShow().getImdbId());
                        if (s == null) {
                            TraktShowResponse sr = new TraktHandler().getByShowName(temporaryMedia.getName());
                            if (sr != null) {
                                s = new Series(sr);
                                seriesRepository.save(s);
                            }
                        }
                        sLock.release();

                        if (s != null) {
                            Episode ep = new Episode(path.toString(), traktData, s);

                            try {
                                episodeSemaphore.acquire();
                            } catch (InterruptedException ex) {
                            }
                            episodeRepository.save(ep);
                            episodeSemaphore.release();
                        }
                    }
                }

                lock.release();
            }

        };

        try {
            lock.acquire();
        } catch (InterruptedException ex) {
        }

        new Thread(r).start();
    }

    @Override
    public void setPaths(List<Path> paths) {
        Iterable<Movie> movies = movieRepository.findAll();
        for (Movie m : movies) {
            boolean foundParent = false;
            for (Path p : paths) {
                if (m.getFilePath().startsWith(p.toString())) {
                    foundParent = true;
                    break;
                }
            }
            if (!foundParent) {
                removeFile(new File(m.getFilePath()).toPath());
            }
        }

        Iterable<Episode> episodes = episodeRepository.findAll();
        for (Episode e : episodes) {
            boolean foundParent = false;
            for (Path p : paths) {
                if (e.getFilePath().startsWith(p.toString())) {
                    foundParent = true;
                    break;
                }
            }
            if (!foundParent) {
                removeFile(new File(e.getFilePath()).toPath());
            }
        }
    }

    @Override
    @Transactional
    public void updateFiles(List<Path> paths, Path basePath) {
        List<Movie> movies;
        try {
            movieSemaphore.acquire();
        } catch (InterruptedException ex) {
        }
        movies = movieRepository.findAllByFilePathStartingWith(basePath.toString());
        movieSemaphore.release();
        for (Movie m : movies) {
            for (int i = 0; i < paths.size(); i++) {
                if (paths.toString().equals(m.getFilePath())) {
                    paths.remove(i);
                    break;
                }
            }
        }

        for (Path p : paths) {
            saveFile(p);
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getFilePath().equals(p.toString())) {
                    movies.remove(i);
                    break;
                }
            }
        }

        for (Movie m : movies) {
            removeFile(new File(m.getFilePath()).toPath());
        }

        List<Episode> episodes = episodeRepository.findAllByFilePathStartingWith(basePath.toString());

        for (Episode m : episodes) {
            for (int i = 0; i < paths.size(); i++) {
                if (paths.toString().equals(m.getFilePath())) {
                    paths.remove(i);
                    break;
                }
            }
        }

        for (Path p : paths) {
            saveFile(p);
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getFilePath().equals(p.toString())) {
                    movies.remove(i);
                    break;
                }
            }
        }

        for (Episode m : episodes) {
            removeFile(new File(m.getFilePath()).toPath());
        }
    }

    @Override
    @Transactional
    public void removeFile(Path path) {
        try {
            movieSemaphore.acquire();
        } catch (InterruptedException ex) {
        }
        List<Movie> movies = movieRepository.findAllByFilePathStartingWith(path.toString());
        for (Movie m : movies) {
            movieRepository.delete(m);
        }
        movieSemaphore.release();
        System.out.println(path);
        List<Episode> episodes = episodeRepository.findAllByFilePathStartingWith(path.toString());
        for (Episode m : episodes) {
            try {
                serieLock.get(m.getSeries().getTitle()).acquire();
            } catch (InterruptedException ex) {
            }
            Series s = m.getSeries();
            s.getEpisodes().remove(m);
            episodeRepository.delete(m);
            if (s.getEpisodes().isEmpty()) {
                seriesRepository.delete(s);
            } else {
                seriesRepository.save(s);
            }
            serieLock.get(m.getSeries().getImdbId()).release();
        }

    }
}
