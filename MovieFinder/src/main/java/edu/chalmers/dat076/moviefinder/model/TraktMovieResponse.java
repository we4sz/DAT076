/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import java.util.List;

/**
 *
 * @author John
 */
public class TraktMovieResponse implements TraktResponse {

    private String title;
    private int year;
    private long released;
    private String url;
    private String trailer;
    private int runtime;
    private String tagline;
    private String overview;
    private String certification;
    private String imdbId;
    private int tmdbId;
    private int rtId;
    private long lastUpdated;
    private TraktImages images;
    private List<String> genres;
    private TraktRatings ratings;
    private TraktPeoples people;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getReleased() {
        return released;
    }

    public void setReleased(long released) {
        this.released = released;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public int getRtId() {
        return rtId;
    }

    public void setRtId(int rtId) {
        this.rtId = rtId;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public TraktImages getImages() {
        return images;
    }

    public void setImages(TraktImages images) {
        this.images = images;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public TraktRatings getRatings() {
        return ratings;
    }

    public void setRatings(TraktRatings ratings) {
        this.ratings = ratings;
    }

    public TraktPeoples getPeople() {
        return people;
    }

    public void setPeople(TraktPeoples people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "TraktMovieResponse{" + "title=" + title + ", year=" + year + ", released=" + released + ", url=" + url + ", trailer=" + trailer + ", runtime=" + runtime + ", tagline=" + tagline + ", overview=" + overview + ", certification=" + certification + ", imdbId=" + imdbId + ", tmdbId=" + tmdbId + ", rtId=" + rtId + ", lastUpdated=" + lastUpdated + ", images=" + images + ", genres=" + genres + ", ratings=" + ratings + ", people=" + people + '}';
    }

   

}
