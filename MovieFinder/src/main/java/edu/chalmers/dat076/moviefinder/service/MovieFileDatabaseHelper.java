package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Peter
 */
@Service
public class MovieFileDatabaseHelper {

    @Autowired
    private TitleParser titleParser;

    @Autowired
    private OmdbHandler omdbHandler;

    @Autowired
    private MovieRepository movieRepository;

    public boolean saveFile(String path, String name) {
        TemporaryMedia temporaryMedia = titleParser.parseMedia(name);
        OmdbMediaResponse omdbData = omdbHandler.getOMDB(temporaryMedia.getName());

        if (omdbData == null) {
            return false;
        } else {
            Movie movie = new Movie(path, omdbData);
            try {
                movieRepository.save(movie);
            } catch (DataIntegrityViolationException e) {
                return false;
            }
        }
        return true;
    }

    public boolean removeFile(String path, String name) {
        Movie movie = movieRepository.findByFilePath(path);
        if (movie != null) {
            movieRepository.delete(movie);
            return true;
        } else {
            return false;
        }
    }
}
