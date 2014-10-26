/* 
 * The MIT License
 *
 * Copyright 2014 Anton, Carl, John, Peter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktShowResponse;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * A class for saving series into the database. It extends Media due to many
 * similar fields.
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
        this.filePath = imdb_id;
    }

    public Series(TraktShowResponse data) {
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
        return "Series{" + super.toString() + "airDay=" + airDay + ", airTime=" + airTime + ", firstAired=" + firstAired + ", episodes=" + episodes + '}';
    }

}
