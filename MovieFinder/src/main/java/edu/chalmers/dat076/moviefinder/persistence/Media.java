/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderColumn;

/**
 *
 * @author Carl Jansson
 */
@MappedSuperclass
public class Media extends AbstractEntity implements Serializable {
    
    
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String filePath;
    @Column(length = 8000)
    private String plot;

    
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    private List<String> genres;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    private List<String> actors;
    
    private Double imdbRating;
    private Integer runtime;
    private Integer releaseYear;

    private String imdbId;
    private String poster;
    
    protected Media(){
    }
    
    public Media(String filePath) {
        this.filePath = filePath;
    }
    
    public Media(String title, String filePath) {
        this.filePath = filePath;
        this.title = title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }
    
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
    
    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    @Override
    public String toString() {
        return "Media{"
                + "title='" + title + '\''
                + ", filePath='" + filePath + '\''
                + '}';
    }
    
}
