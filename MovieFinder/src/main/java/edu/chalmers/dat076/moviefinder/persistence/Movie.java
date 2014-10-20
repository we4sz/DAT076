/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

/**
 * A movie entity.
 *
 * @author Peter
 */
@Entity
public class Movie extends AbstractEntity {

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String filePath;
    private Double imdbRating;
    private Integer runtime;
    private String plot;
    private String releaseYear;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> genres;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> actors;
    private String imdbId;

    protected Movie() {
    }

    public Movie(String filePath, String title) {
        this.filePath = filePath;
        this.title = title;
    }

    public Movie(String filePath, OmdbMediaResponse omdb) {
        this.filePath = filePath;
        if (omdb != null) {
            title = omdb.getTitle();
            imdbRating = omdb.getImdbRating();
            releaseYear = omdb.getYear();
            plot = omdb.getPlot();
            imdbId = omdb.getImdbID();
            System.out.println(omdb.getRuntime());
            if(!omdb.getRuntime().equals("N/A")){
                runtime = Integer.parseInt(omdb.getRuntime().substring(0, omdb.getRuntime().indexOf(" ")));
            }
            actors = new ArrayList<String>(Arrays.asList(omdb.getActors().split(", ")));
            genres = new ArrayList<String>(Arrays.asList(omdb.getGenre().split(", ")));
        }
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getPlot() {
        return plot;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getRuntime() {
        return runtime;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "title='" + title + '\''
                + ", filePath='" + filePath + '\''
                + ", imdbRating=" + imdbRating
                + ", runtime=" + runtime
                + '}';
    }
}
