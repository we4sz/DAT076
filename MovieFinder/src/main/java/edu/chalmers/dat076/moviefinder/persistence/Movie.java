/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import edu.chalmers.dat076.moviefinder.model.TVDBData;
import edu.chalmers.dat076.moviefinder.model.TVDBEpisode;
import edu.chalmers.dat076.moviefinder.model.TVDBSerie;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

/**
 * A movie entity.
 *
 * @author Peter
 */
@Entity
public class Movie extends AbstractEntity implements Serializable {

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String filePath;
    private Double imdbRating;
    private Integer runtime;
    @Column(length = 8000)
    private String plot;
    private String poster;
    private Integer releaseYear;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> genres;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> actors;
    private String imdbId;

    protected Movie() {
    }

    public Movie(String title, String filePath) {
        this.filePath = filePath;
        this.title = title;
    }

    public Movie(String filePath, OmdbMediaResponse omdb) {
        this.filePath = filePath;
        if (omdb != null) {
            title = omdb.getTitle();
            imdbRating = omdb.getImdbRating();
            releaseYear = Integer.parseInt(omdb.getYear().substring(0, 4));
            plot = omdb.getPlot();
            imdbId = omdb.getImdbID();
            poster = omdb.getPoster();
            if (!omdb.getRuntime().equals("N/A")) {
                runtime = Integer.parseInt(omdb.getRuntime().substring(0, omdb.getRuntime().indexOf(" ")));
            }
            actors = Arrays.asList(omdb.getActors().split(", "));
            genres = Arrays.asList(omdb.getGenre().split(", "));
        }
    }

    public Movie(String filePath, TVDBData data) {
        this.filePath = filePath;
        if (data != null) {
            title = data.getEpisode().getEpisodeName();
            imdbRating = data.getEpisode().getRating();
            poster = data.getSerie().getPoster();
            releaseYear = Integer.parseInt(data.getEpisode().getFirstAired().substring(0, 4));
            plot = data.getEpisode().getOverview();
            runtime = data.getSerie().getRuntime();
            actors = Arrays.asList(data.getSerie().getActors().split("|"));
            genres = Arrays.asList(data.getSerie().getGenre().split("|"));
        }
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getPoster() {
        return poster;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getPlot() {
        return plot;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getRuntime() {
        return runtime;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "title='" + title + '\''
                + ", filePath='" + filePath + '\''
                + ", imdbRating=" + imdbRating
                + ", runtime=" + runtime
                + '}';
    }
}
