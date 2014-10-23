/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

/**
 *
 * @author John
 */
public class TraktEpisode {

    private int season;
    private int number;
    private int tvdb_id;
    private String imdb_id;
    private String title;
    private String overview;
    private String url;
    private int first_aired;
    private String first_aired_iso;
    private int first_aired_utc;
    private TraktImages images;
    private TraktRatings ratings;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTvdb_id() {
        return tvdb_id;
    }

    public void setTvdb_id(int tvdb_id) {
        this.tvdb_id = tvdb_id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFirst_aired() {
        return first_aired;
    }

    public void setFirst_aired(int first_aired) {
        this.first_aired = first_aired;
    }

    public String getFirst_aired_iso() {
        return first_aired_iso;
    }

    public void setFirst_aired_iso(String first_aired_iso) {
        this.first_aired_iso = first_aired_iso;
    }

    public int getFirst_aired_utc() {
        return first_aired_utc;
    }

    public void setFirst_aired_utc(int first_aired_utc) {
        this.first_aired_utc = first_aired_utc;
    }

    public TraktImages getImages() {
        return images;
    }

    public void setImages(TraktImages images) {
        this.images = images;
    }

    public TraktRatings getRatings() {
        return ratings;
    }

    public void setRatings(TraktRatings ratings) {
        this.ratings = ratings;
    }

}
