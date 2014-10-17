/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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

    protected Movie() {
    }
    
    public Movie(String title, String filePath) {
        this(title, filePath, null);
    }
    
    public Movie(String title, String filePath, Double imdbRating) {
        this.title = title;
        this.filePath = filePath;
        this.imdbRating = imdbRating;
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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", filePath='" + filePath + '\'' +
                ", imdbRating=" + imdbRating +
                '}';
    }
}
