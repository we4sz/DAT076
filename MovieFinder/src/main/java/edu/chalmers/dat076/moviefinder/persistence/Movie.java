/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;

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
    private String runtime;

    protected Movie() {
    }
    
    public Movie(String title, String filePath) {
        this(title, filePath, null, null);
    }
    
    public Movie(String title, String filePath, Double imdbRating, String runtime) {
        this.title = title;
        this.filePath = filePath;
        this.imdbRating = imdbRating;
        this.runtime = runtime;
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
    
    public String getRuntime() {
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
