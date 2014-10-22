package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import java.io.File;
import java.io.IOException;
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
    private OmdbHandler omdbHandler;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void saveFile(final Path path) {
        Runnable r = new Runnable() {
            @Override
            @Transactional
            public void run() {

                TemporaryMedia temporaryMedia = new TitleParser().parseMedia(path.getFileName().toString());
                Movie movie = null;

                if (temporaryMedia.IsMovie()) {
                    OmdbMediaResponse omdbData = omdbHandler.getByTmpMedia(temporaryMedia);
                    if (omdbData != null) {
                        movie = new Movie(path.toString(), omdbData);
                    }
                } else {
                    try {
                        movie = new Movie(path.toString(), new TVDBHandler().getEpisodeAndSerie(temporaryMedia));
                    } catch (IOException | NullPointerException e) {
                    }
                }

                if (movie != null) {
                    try {
                        synchronized (movieRepository) {
                            movieRepository.save(movie);
                        }
                    } catch (DataIntegrityViolationException e) {
                    }
                }
            }
        };

        new Thread(r).start();
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
