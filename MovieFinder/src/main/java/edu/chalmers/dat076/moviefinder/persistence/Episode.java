/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TVDBData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Carl Jansson
 */
@Entity
public class Episode extends Media implements Serializable {
    
    @Column(nullable = false)
    private Integer season;
    @Column(nullable = false)
    private Integer episode;
    
    //private String sID;
    
    protected Episode() {
    }

    public Episode(String title, String filePath, int season, int episode) {
        super(title, filePath);
        this.season = season;
        this.episode = episode;
    }
    
    public Episode(String filePath, TVDBData data) {
        super(filePath);
        if (data != null) {
            setTitle(data.getEpisode().getEpisodeName());
            this.season = data.getEpisode().getSeasonNumber();
            this.episode = data.getEpisode().getEpisodeNumber();
            //this.sID = data.getSerie().getId();
            setImdbRating(data.getEpisode().getRating());
            setPoster("http://thetvdb.com/banners/"+data.getSerie().getPoster());
            setReleaseYear(Integer.parseInt(data.getEpisode().getFirstAired().substring(0, 4)));
            setPlot(data.getEpisode().getOverview());
            setRuntime(data.getSerie().getRuntime());
            setGenres( Arrays.asList(data.getSerie().getGenre().split(",")));
            
            List<String> actors = new ArrayList<>();
            actors.addAll(Arrays.asList(data.getSerie().getActors().split(",")));
            actors.addAll(Arrays.asList(data.getEpisode().getGuestStars().split(",")));
            setActors(actors);
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    /*public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }*/
    
    
    
    
}
