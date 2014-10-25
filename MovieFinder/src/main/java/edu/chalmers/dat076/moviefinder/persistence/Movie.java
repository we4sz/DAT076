/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * A movie entity. All fields defined in Media. This class is simply the
 * receiver of a TraktMovieResponse so it is saved correctly.
 *
 * @author Peter
 */
@Entity
public class Movie extends Media implements Serializable {

    protected Movie() {
    }

    public Movie(String title, String filePath) {
        super(title, filePath);
        
    }
    
    public Movie(String filePath, TraktResponse data) {
        setFilePath( filePath);
        if (data != null) {
            if (data instanceof TraktMovieResponse) {
                setMovie(filePath, (TraktMovieResponse) data);
            } 
        }
    }

    
    public Movie(String filePath, TraktMovieResponse data) {
        setMovie(filePath, data);
    }
    
    private void setMovie(String filePath, TraktMovieResponse data){
        if (data != null) {
            setFilePath(filePath);
            setTitle(data.getTitle());
            setImdbRating(data.getRatings().getPercentage() / 10.0);
            setReleaseTime(data.getReleased());
            setPlot(data.getOverview());
            setImdbId(data.getImdbId());
            setRuntime(data.getRuntime());
            setActors(toActors(data.getPeople().getActors()));
            setGenres(data.getGenres());
            setPoster(getImage(data.getImages()));
            setRated(data.getCertification());
            setDirector(data.getPeople().getDirectors().size() > 0 ? data.getPeople().getDirectors().get(0).getName() : null);
        }
    }
    
}
