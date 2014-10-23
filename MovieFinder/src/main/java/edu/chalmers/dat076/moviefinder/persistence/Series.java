/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktImages;
import edu.chalmers.dat076.moviefinder.model.TraktRatings;
import edu.chalmers.dat076.moviefinder.model.TraktShow;
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

    
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String imdbId;
    
    private Integer year;
    private String url;
    private Integer first_aired;
    private String first_aired_iso;
    private Integer first_aired_utc;
    private String country;
    private String overview;
    private Integer runtime;
    private String network;
    private String air_day;
    private String air_time;
    private String certification;
    private Integer tvdb_id;
    private Integer tvrage_id;
    //private TraktImages images;
    //private TraktRatings ratings;
    //private List<String> genres;
    

    protected Series() {
    }

    public Series(String seriesName, String imdb_id) {
        this.episodes = new ArrayList<>();
        this.title = seriesName;
        this.imdbId = imdb_id;
    }
    
    public Series(TraktShow data) {
        if (data != null) {
            year = data.getYear();
            url = data.getUrl();
            first_aired = data.getFirst_aired();
            first_aired_iso = data.getFirst_aired_iso();
            first_aired_utc = data.getFirst_aired_utc();
            country = data.getCountry();
            overview = data.getOverview();
            runtime = data.getRuntime();
            network = data.getNetwork();
            air_day = data.getAir_day();
            air_time = data.getAir_time();
            certification = data.getCertification();
            tvdb_id = data.getTvdb_id();
            tvrage_id = data.getTvrage_id();
            //images = data.getImages();
            //ratings = data.getRatings();
            //genres = data.getGenres();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdb_id) {
        this.imdbId = imdb_id;
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

    public String getAir_time() {
        return air_time;
    }

    public void setAir_time(String air_time) {
        this.air_time = air_time;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
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

    /*public TraktImages getImages() {
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }*/

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    
    public void addEpisodes(Episode e) {
        this.episodes.add(e);
    }

    @Override
    public String toString() {
        return "Series{" + "episodes=" + episodes + ", title=" + title 
                + ", imdb_id=" + imdbId + ", year=" + year + ", url=" 
                + url + ", first_aired=" + first_aired + ", first_aired_iso=" 
                + first_aired_iso + ", first_aired_utc=" + first_aired_utc 
                + ", country=" + country + ", overview=" + overview 
                + ", runtime=" + runtime + ", network=" + network 
                + ", air_day=" + air_day + ", air_time=" + air_time 
                + ", certification=" + certification + ", tvdb_id=" + tvdb_id 
                + ", tvrage_id=" + tvrage_id + ", images=" /*+ images "Series{" + "episodes=" + episodes + ", title=" + title 
                + ", imdb_id=" + imdbId + ", year=" + year + ", url=" 
                + url + ", first_aired=" + first_aired + ", first_aired_iso=" 
                + first_aired_iso + ", first_aired_utc=" + first_aired_utc 
                + ", country=" + country + ", overview=" + overview 
                + ", runtime=" + runtime + ", network=" + network 
                + ", air_day=" + air_day + ", air_time=" + air_time 
                + ", certification=" + certification + ", tvdb_id=" + tvdb_id 
                + ", tvrage_id=" + tvrage_id + ", images=" /*+ images 
                + ", ratings=" + ratings + ", genres=" + genres*/ + '}';
    }

    
    
}
