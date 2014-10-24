/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    @JsonBackReference
    private Series series;

    protected Episode() {
    }

    public Episode(String title, String filePath, int season, int episode) {
        super(title, filePath);
        this.season = season;
        this.episode = episode;
    }

    public Episode(String filePath, TraktResponse data, Series s) {
        this.series = s;
        if (data != null) {
            if (data instanceof TraktEpisodeResponse) {
                setEpisode(filePath, (TraktEpisodeResponse) data);
            }
        }
    }

    public Episode(String filePath, TraktEpisodeResponse data) {
        setEpisode(filePath, data);
    }

    private void setEpisode(String filePath, TraktEpisodeResponse data) {

        if (data != null) {
            this.season = data.getEpisode().getSeason();
            this.episode = data.getEpisode().getNumber();
            setFilePath(filePath);
            setTitle(data.getEpisode().getTitle());
            setImdbRating(data.getEpisode().getRatings().getPercentage() / 10.0);     
            setReleaseTime(data.getEpisode().getFirstAired());
            setPlot(data.getEpisode().getOverview());
            setImdbId(data.getEpisode().getImdbId());
            setRuntime(data.getShow().getRuntime());
            setActors(new LinkedList<Actor>());
            setGenres(data.getShow().getGenres());
            setPoster(getImage(data.getShow().getImages()));
            setRated(data.getShow().getCertification());
            setDirector(null);
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

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Episode{" + super.toString() + "season=" + season + ", episode=" + episode + '}';
    }
}
