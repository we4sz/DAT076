package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import java.io.IOException;
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
    private TitleParser titleParser;

    @Autowired
    private TVDBHandler tvdbHandler;

    @Autowired
    private OmdbHandler omdbHandler;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public boolean saveFile(String path, String name) {
        TemporaryMedia temporaryMedia = titleParser.parseMedia(name);
        Movie movie = null;
        
        if (temporaryMedia.IsMovie()) {
            OmdbMediaResponse omdbData = omdbHandler.getByTmpMedia(temporaryMedia);
            if (omdbData != null) {
                movie = new Movie(path, omdbData);
            }
        } else {
            try {
                movie = new Movie(path, tvdbHandler.getEpisodeAndSerie(temporaryMedia));
            } catch (IOException | NullPointerException e) {
                return false;
            }
        }
        
        if (movie != null) {
            try {
                movieRepository.save(movie);
            } catch (DataIntegrityViolationException e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
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
