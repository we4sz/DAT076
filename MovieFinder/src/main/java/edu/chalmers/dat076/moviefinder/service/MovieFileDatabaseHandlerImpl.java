package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import edu.chalmers.dat076.moviefinder.model.TraktShowReponse;
import edu.chalmers.dat076.moviefinder.persistence.Episode;
import edu.chalmers.dat076.moviefinder.persistence.EpisodeRepository;
import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import edu.chalmers.dat076.moviefinder.persistence.Series;
import edu.chalmers.dat076.moviefinder.persistence.SeriesRepository;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A service for saving and removing files from the database. New files are
 * looked up via OMDB to try and get information about them.
 *
 * @author Peter
 */
@Service
public class MovieFileDatabaseHandlerImpl implements MovieFileDatabaseHandler {

    @Autowired
    private TraktHandler traktHandler;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public void saveFile(final Path path) {
        Runnable r = new Runnable() {
            @Override
            @Transactional
            public void run() {

                TemporaryMedia temporaryMedia = new TitleParser().parseMedia(path.getFileName().toString());
                TraktResponse traktData = traktHandler.getByTmpMedia(temporaryMedia);
                if (traktData != null) {
                    if (traktData instanceof TraktMovieResponse) {
                        Movie movie = new Movie(path.toString(), traktData);
                        try {
                            synchronized (movieRepository) {
                                movieRepository.save(movie);
                            }
                        } catch (DataIntegrityViolationException e) {
                        }
                    } else {
                        TraktEpisodeResponse epr = (TraktEpisodeResponse) traktData;
                        Series s;
                        synchronized (seriesRepository) {
                            s = seriesRepository.findByImdbId(epr.getShow().getImdbId());

                            if (s == null) {
                                TraktShowReponse sr = traktHandler.getByShowName(temporaryMedia.getName());
                                if (sr != null) {
                                    synchronized (seriesRepository) {
                                        s = new Series(sr);
                                        seriesRepository.save(s);
                                    }
                                }
                            }
                        }
                        if (s != null) {
                            Episode ep = new Episode(path.toString(), traktData, s);
                            synchronized (episodeRepository) {
                                episodeRepository.save(ep);
                            }
                        }
                    }
                }

            }

        };

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
    }

    @Override
    @Transactional
    public void updateFiles(List<Path> paths, Path basePath) {
        List<Movie> movies;
        synchronized (movieRepository) {
            movies = movieRepository.findAllByFilePathStartingWith(basePath.toString());
        }
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
    }

    @Override
    @Transactional
    public void removeFile(Path path) {
        synchronized (movieRepository) {
            List<Movie> movies = movieRepository.findAllByFilePathStartingWith(path.toString());
            for (Movie m : movies) {
                movieRepository.delete(m);
            }
        }
    }
}
