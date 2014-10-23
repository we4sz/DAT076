/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktActor;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktImages;
import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private Integer releaseYear;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    private List<String> genres;

    //@ElementCollection(fetch = FetchType.EAGER)
    //@CollectionTable(name = "Actors", joinColumns = @JoinColumn(name = "movie_id"))
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Actor> actors;

    private String imdbId;
    private String poster;
    private String rated;
    private String country;
    private String director;

    protected Movie() {
    }

    public Movie(String title, String filePath) {
        this.filePath = filePath;
        this.title = title;
    }

    public Movie(String filePath, TraktResponse data) {
        this.filePath = filePath;
        if (data != null) {
            if (data instanceof TraktMovieResponse) {
                setMovie((TraktMovieResponse) data);
            } else {
                setSerie((TraktEpisodeResponse) data);
            }
        }
    }

    private void setSerie(TraktEpisodeResponse data) {
        title = data.getEpisode().getTitle();
        imdbRating = data.getEpisode().getRatings().getPercentage() / 10.0;
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(data.getEpisode().getFirst_aired());
        releaseYear = c.get(Calendar.YEAR);
        plot = data.getEpisode().getOverview();
        imdbId = data.getEpisode().getImdb_id();
        runtime = data.getShow().getRuntime();
        actors = new LinkedList<>();
        genres = data.getShow().getGenres();
        poster = getImage(data.getShow().getImages());
        rated = data.getShow().getCertification();
        director = null;
    }

    private void setMovie(TraktMovieResponse data) {
        title = data.getTitle();
        imdbRating = data.getRatings().getPercentage() / 10.0;
        releaseYear = data.getYear();
        plot = data.getOverview();
        imdbId = data.getImdb_id();
        runtime = data.getRuntime();
        actors = toActors(data.getPeople().getActors());
        genres = data.getGenres();
        poster = getImage(data.getImages());
        rated = data.getCertification();
        director = data.getPeople().getDirectors().size() > 0 ? data.getPeople().getDirectors().get(0).getName() : null;
    }

    private List<Actor> toActors(List<TraktActor> actors) {
        List<Actor> _a = new LinkedList<>();
        for (TraktActor a : actors) {
            _a.add(new Actor(a));
        }
        return _a;
    }

    private String getImage(TraktImages i) {
        if (i.getPoster() != null) {
            return i.getPoster();
        } else if (i.getFanart() != null) {
            return i.getFanart();
        } else if (i.getBanner() != null) {
            return i.getBanner();
        } else if (i.getScreen() != null) {
            return i.getScreen();
        } else if (i.getHeadshot() != null) {
            return i.getHeadshot();
        }
        return null;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<String> getGenres() {
        return genres;
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

    public String getPoster() {
        return poster;
    }

    public String getRated() {
        return rated;
    }

    public String getCountry() {
        return country;
    }

    public String getDirector() {
        return director;
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
