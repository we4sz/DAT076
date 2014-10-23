/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import java.io.Serializable;
import org.xmappr.Element;

/**
 *
 * @author John
 */
public class TVDBSerie implements Serializable {

    @Element
    private String id;
    @Element
    private String Actors;
    @Element
    private String Monday;
    @Element
    private String Airs_Time;
    @Element
    private String ContentRating;
    @Element
    private String FirstAired;
    @Element
    private String Genre;
    @Element
    private String IMDB_ID;
    @Element
    private String Language;
    @Element
    private String Overview;
    @Element(defaultValue = "-1")
    private double rating;
    @Element(defaultValue = "-1")
    private int Runtime;
    @Element
    private String SeriesID;
    @Element
    private String SeriesName;
    @Element
    private String Status;
    @Element
    private String banner;
    @Element
    private String fanart;
    @Element(defaultValue = "-1")
    private long lastupdated;
    @Element
    private String poster;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    public String getMonday() {
        return Monday;
    }

    public void setMonday(String Monday) {
        this.Monday = Monday;
    }

    public String getAirs_Time() {
        return Airs_Time;
    }

    public void setAirs_Time(String Airs_Time) {
        this.Airs_Time = Airs_Time;
    }

    public String getContentRating() {
        return ContentRating;
    }

    public void setContentRating(String ContentRating) {
        this.ContentRating = ContentRating;
    }

    public String getFirstAired() {
        return FirstAired;
    }

    public void setFirstAired(String FirstAired) {
        this.FirstAired = FirstAired;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getIMDB_ID() {
        return IMDB_ID;
    }

    public void setIMDB_ID(String IMDB_ID) {
        this.IMDB_ID = IMDB_ID;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String Overview) {
        this.Overview = Overview;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRuntime() {
        return Runtime;
    }

    public void setRuntime(int Runtime) {
        this.Runtime = Runtime;
    }

    public String getSeriesID() {
        return SeriesID;
    }

    public void setSeriesID(String SeriesID) {
        this.SeriesID = SeriesID;
    }

    public String getSeriesName() {
        return SeriesName;
    }

    public void setSeriesName(String SeriesName) {
        this.SeriesName = SeriesName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getFanart() {
        return fanart;
    }

    public void setFanart(String fanart) {
        this.fanart = fanart;
    }

    public long getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(long lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}
