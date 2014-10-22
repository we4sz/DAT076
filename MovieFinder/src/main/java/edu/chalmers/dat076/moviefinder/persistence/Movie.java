/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import edu.chalmers.dat076.moviefinder.model.TVDBData;
import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Entity;

/**
 * A movie entity.
 *
 * @author Peter
 */
@Entity
public class Movie extends Media implements Serializable {

    
    private String rated;
    private String country;
    private String director;

    protected Movie() {
    }

    public Movie(String title, String filePath) {
        super(title, filePath);
        
    }

    public Movie(String filePath, OmdbMediaResponse omdb) {
        super(filePath);
        if (omdb != null) {
            setTitle(omdb.getTitle());
            setImdbRating( omdb.getImdbRating());
            setReleaseYear( Integer.parseInt(omdb.getYear().substring(0, 4)));
            setPlot(omdb.getPlot());
            setImdbId( omdb.getImdbID());
            if (!omdb.getRuntime().equals("N/A")) {
                setRuntime(Integer.parseInt(omdb.getRuntime().substring(0, omdb.getRuntime().indexOf(" "))));
            }
            setActors( Arrays.asList(omdb.getActors().split(", ")));
            setGenres( Arrays.asList(omdb.getGenre().split(", ")));
            setPoster(omdb.getPoster());
            this.rated = omdb.getRated();
            this.country = omdb.getCountry();
            this.director = omdb.getDirector();
        }
    }

    //TODO remove this. Replaced by its own entity
    public Movie(String filePath, TVDBData data) {
        super(filePath);
        if (data != null) {
            setTitle(data.getEpisode().getEpisodeName());
            setImdbRating(data.getEpisode().getRating());
            setPoster("http://thetvdb.com/banners/"+data.getSerie().getPoster());
            setReleaseYear(Integer.parseInt(data.getEpisode().getFirstAired().substring(0, 4)));
            setPlot(data.getEpisode().getOverview());
            setRuntime(data.getSerie().getRuntime());
            setActors(Arrays.asList(data.getSerie().getActors().split("|")));
            setGenres( Arrays.asList(data.getSerie().getGenre().split("|")));
        }
    }

    public String getRated() {
        return rated;
    }

    public String getCountry() {
        return country;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Movie{"
                + super.toString()
                + "rated='" + rated + '\''
                + ", country='" + country + '\''
                + ", director='" + director + '\''
                + '}';
    }
}
