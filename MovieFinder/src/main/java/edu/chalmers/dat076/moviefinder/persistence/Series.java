/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TVDBSerie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Carl Jansson
 */
@Entity
public class Series extends AbstractEntity implements Serializable {

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "series_id")
    private List<Episode> episodes;

    @Column(nullable = false, unique = true)
    private String sID; // in TVDBSerie named id
    @Column(nullable = false)
    private String seriesName;

    private String status;
    private String poster;
    private String IMDB_ID;
    private Double rating;

    protected Series() {
    }

    public Series(String seriesName, String sID) {
        this.episodes = new ArrayList<>();
        this.seriesName = seriesName;
        this.sID = sID;
    }

    public Series(TVDBSerie data) {
        if (data != null) {
            this.episodes = new ArrayList<>();
            this.sID = data.getId();
            this.seriesName = data.getSeriesName();
            this.status = data.getStatus();
            this.poster = data.getPoster();
            this.IMDB_ID = data.getIMDB_ID();
            this.rating = data.getRating();
        }
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String SeriesName) {
        this.seriesName = SeriesName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getIMDB_ID() {
        return IMDB_ID;
    }

    public void setIMDB_ID(String IMDB_ID) {
        this.IMDB_ID = IMDB_ID;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    
    public void addEpisodes(Episode e) {
        this.episodes.add(e);
    }
}
