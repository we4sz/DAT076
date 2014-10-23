/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktActor;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktImages;
import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Entity;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/**
 * A movie entity.
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
        if (data != null) {
            setFilePath(filePath);
            setTitle(data.getTitle());
            setImdbRating(data.getRatings().getPercentage() / 10.0);
            setReleaseYear(data.getYear());
            setPlot(data.getOverview());
            setImdbId(data.getImdb_id());
            setRuntime(data.getRuntime());
            setActors(toActors(data.getPeople().getActors()));
            setGenres(data.getGenres());
            setPoster(getImage(data.getImages()));
            setRated(data.getCertification());
            setDirector(data.getPeople().getDirectors().size() > 0 ? data.getPeople().getDirectors().get(0).getName() : null);
        }
    }
    
    private void setMovie(String filePath, TraktMovieResponse data){
        if (data != null) {
            setFilePath(filePath);
            setTitle(data.getTitle());
            setImdbRating(data.getRatings().getPercentage() / 10.0);
            setReleaseYear(data.getYear());
            setPlot(data.getOverview());
            setImdbId(data.getImdb_id());
            setRuntime(data.getRuntime());
            setActors(toActors(data.getPeople().getActors()));
            setGenres(data.getGenres());
            setPoster(getImage(data.getImages()));
            setRated(data.getCertification());
            setDirector(data.getPeople().getDirectors().size() > 0 ? data.getPeople().getDirectors().get(0).getName() : null);
        }
    }
    
}
