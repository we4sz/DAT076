/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.xmappr.Element;

/**
 *
 * @author John
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TVDBEpisode {

    //http://thetvdb.com/api/8E78D675E1E52FDE/series/73255/default/3/2/en.xml

    @Element
    private String id;
    @Element
    private String seasonid;
    @Element(defaultValue = "-1")
    private int EpisodeNumber;
    @Element
    private String EpisodeName;
    @Element
    private String FirstAired;
    @Element
    private String GuestStars;
    @Element
    private String Director;
    @Element
    private String Writer;
    @Element
    private String Overview;
    @Element(defaultValue = "-1")
    private int absolute_number;
    @Element
    private String filename;
    @Element
    private String seriesid;
    @Element(defaultValue = "-1")
    private int thumb_width;
    @Element(defaultValue = "-1")
    private int thumb_height;
    @Element
    private String IMDB_ID;
    @Element(defaultValue = "-1")
    private double Rating;
    @Element(defaultValue = "-1")
    private int SeasonNumber;
    @Element
    private String Language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeasonid() {
        return seasonid;
    }

    public void setSeasonid(String seasonid) {
        this.seasonid = seasonid;
    }

    public int getEpisodeNumber() {
        return EpisodeNumber;
    }

    public void setEpisodeNumber(int EpisodeNumber) {
        this.EpisodeNumber = EpisodeNumber;
    }

    public String getEpisodeName() {
        return EpisodeName;
    }

    public void setEpisodeName(String EpisodeName) {
        this.EpisodeName = EpisodeName;
    }

    public String getFirstAired() {
        return FirstAired;
    }

    public void setFirstAired(String FirstAired) {
        this.FirstAired = FirstAired;
    }

    public String getGuestStars() {
        return GuestStars;
    }

    public void setGuestStars(String GuestStars) {
        this.GuestStars = GuestStars;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String Writer) {
        this.Writer = Writer;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String Overview) {
        this.Overview = Overview;
    }

    public int getAbsolute_number() {
        return absolute_number;
    }

    public void setAbsolute_number(int absolute_number) {
        this.absolute_number = absolute_number;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(String seriesid) {
        this.seriesid = seriesid;
    }

    public int getThumb_width() {
        return thumb_width;
    }

    public void setThumb_width(int thumb_width) {
        this.thumb_width = thumb_width;
    }

    public int getThumb_height() {
        return thumb_height;
    }

    public void setThumb_height(int thumb_height) {
        this.thumb_height = thumb_height;
    }

    public String getIMDB_ID() {
        return IMDB_ID;
    }

    public void setIMDB_ID(String IMDB_ID) {
        this.IMDB_ID = IMDB_ID;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double Rating) {
        this.Rating = Rating;
    }

    public int getSeasonNumber() {
        return SeasonNumber;
    }

    public void setSeasonNumber(int SeasonNumber) {
        this.SeasonNumber = SeasonNumber;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

}
