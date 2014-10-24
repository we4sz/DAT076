/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktShowReponse;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Carl Jansson
 */
@Entity
public class Series extends Media implements Serializable {

    private String airDay;
    private String airTime;
    private Long firstAired;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Episode> episodes;

    public Series() {
    }

    public Series(String seriesName, String imdb_id) {
        this.title = seriesName;
        this.imdbId = imdb_id;
    }

    public Series(TraktShowReponse data) {
        if (data != null) {
            setReleaseTime(data.getFirstAired());
            firstAired = data.getFirstAired();
            filePath = data.getImdbId();
            country = data.getCountry();
            plot = data.getOverview();
            runtime = data.getRuntime();
            airDay = data.getAirDay();
            airTime = data.getAirTime();
            rated = data.getCertification();
            imdbId = data.getImdbId();
            title = data.getTitle();
            poster = getImage(data.getImages());
            imdbRating = data.getRatings().getPercentage() / 10.0;
            genres = data.getGenres();
            actors = toActors(data.getPeople().getActors());
        }
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public String getAirDay() {
        return airDay;
    }

    public void setAirDay(String airDay) {
        this.airDay = airDay;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public Long getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(Long firstAired) {
        this.firstAired = firstAired;
    }

    @Override
    public String toString() {
        return "Series{" + "airDay=" + airDay + ", airTime=" + airTime + ", firstAired=" + firstAired + ", episodes=" + episodes + '}';
    }

}
