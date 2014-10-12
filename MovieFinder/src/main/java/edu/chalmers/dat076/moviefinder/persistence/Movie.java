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
    
    private Double imdbRating;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> genres = new HashSet<>();

    protected Movie() {
    }
    
    public Movie(String title) {
        this(title, null, null);
    }
    
    public Movie(String title, Double imdbRating, Set<String> genres) {
        this.title = title;
        this.imdbRating = imdbRating;
        if(genres != null) {
            this.genres = new HashSet<>(genres);
        }
    }

    public String getTitle() {
        return title;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public Set<String> getGenres() {
        return Collections.unmodifiableSet(genres);
    }
}
