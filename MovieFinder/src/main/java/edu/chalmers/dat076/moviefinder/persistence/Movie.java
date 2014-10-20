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
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
    private int runtime;
    private String plot;
    private int year;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private String imdbId;
    

    protected Movie() {
    }
    
    public Movie(String filePath) {
        this(filePath, null);
    }
    
    public Movie(String filePath, OmdbMediaResponse omdb) {
        this.filePath = filePath;
        if(omdb != null){
            title = omdb.getTitle();
            imdbRating = omdb.getImdbRating();
            year = omdb.getYear();
            plot = omdb.getPlot();
            imdbId = omdb.getImdbID();
            runtime = Integer.parseInt(omdb.getRuntime().substring(0, omdb.getRuntime().indexOf(" ")));
            actors = new ArrayList<String>(Arrays.asList(omdb.getActors().split(", ")));
            genres = new ArrayList<String>(Arrays.asList(omdb.getGenre().split(", ")));
        }
    }

    @ManyToOne
    public List<String> getActors() {
        return actors;
    }

    @ManyToOne
    public List<String> getGenres() {
        return genres;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getPlot() {
        return plot;
    }

    public int getYear() {
        return year;
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
    
    public int getRuntime() {
        return runtime;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", filePath='" + filePath + '\'' +
                ", imdbRating=" + imdbRating +
                ", runtime=" + runtime +
                '}';
    }
}
