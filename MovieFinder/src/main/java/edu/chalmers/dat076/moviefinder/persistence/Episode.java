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

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * An Episode entity. It has all fields a movie has but adds season, episode and
 * an reference to what series it is part of.
 *
 * @author Carl Jansson
 */
@Entity
public class Episode extends Media implements Serializable {
    private static final long serialVersionUID = 1L;

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
