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
public class TraktShowReponse implements TraktResponse{

    private String title;
    private int year;
    private String url;
    private int first_aired;
    private String first_aired_iso;
    private int first_aired_utc;
    private String country;
    private String overview;
    private int runtime;
    private String status;
    private String network;
    private String air_day;
    private String air_day_utc;
    private String air_time;
    private String air_time_utc;
    private String certification;
    private String imdb_id;
    private int tvdb_id;
    private int tvrage_id;
    private int last_updated;
    private String poster;
    private TraktImages images;
    private TraktRatings ratings;
    private TraktPeoples people;
    private List<String> genres;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getAir_day() {
        return air_day;
    }

    public void setAir_day(String air_day) {
        this.air_day = air_day;
    }

    public String getAir_day_utc() {
        return air_day_utc;
    }

    public void setAir_day_utc(String air_day_utc) {
        this.air_day_utc = air_day_utc;
    }

    public String getAir_time() {
        return air_time;
    }

    public void setAir_time(String air_time) {
        this.air_time = air_time;
    }

    public String getAir_time_utc() {
        return air_time_utc;
    }

    public void setAir_time_utc(String air_time_utc) {
        this.air_time_utc = air_time_utc;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public int getTvdb_id() {
        return tvdb_id;
    }

    public void setTvdb_id(int tvdb_id) {
        this.tvdb_id = tvdb_id;
    }

    public int getTvrage_id() {
        return tvrage_id;
    }

    public void setTvrage_id(int tvrage_id) {
        this.tvrage_id = tvrage_id;
    }

    public int getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(int last_updated) {
        this.last_updated = last_updated;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public TraktPeoples getPeople() {
        return people;
    }

    public void setPeople(TraktPeoples people) {
        this.people = people;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    
    

}
