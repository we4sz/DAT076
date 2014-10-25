/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktActor;
import edu.chalmers.dat076.moviefinder.model.TraktImages;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Abstract class for saving media. Main use is for Movies and Episodes.
 *
 * @author Carl Jansson
 */
@MappedSuperclass
public abstract class Media extends AbstractEntity implements Serializable {

    @Column(nullable = false)
    protected String title;
    @Column(nullable = false, unique = true)
    protected String filePath;
    protected Double imdbRating;
    protected Integer runtime;
    @Column(length = 8000)
    protected String plot;
    protected Integer releaseYear;
    protected Integer releaseMonth;
    protected Integer releaseDay;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    protected List<String> genres;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    protected List<Actor> actors;

    protected String imdbId;
    protected String poster;
    protected String rated;
    protected String country;
    protected String director;

    protected Media() {
    }

    public Media(String title, String filePath) {
        this.filePath = filePath;
        this.title = title;
    }

    protected static List<Actor> toActors(List<TraktActor> actors) {
        List<Actor> _a = new LinkedList<>();
        for (TraktActor a : actors) {
            _a.add(new Actor(a));
        }
        return _a;
    }

    protected static String getImage(TraktImages i) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     * Sets day, month and year that this Media was released.
     *
     * @param seconds
     */
    public void setReleaseTime(long seconds) {
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(seconds * 1000);
        setReleaseDay(c.get(Calendar.DAY_OF_MONTH));
        setReleaseMonth(c.get(Calendar.MONTH));
        setReleaseYear(c.get(Calendar.YEAR));
    }

    public Integer getReleaseDay() {
        return releaseDay;
    }

    public Integer getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseDay(Integer releaseDay) {
        this.releaseDay = releaseDay;
    }

    public void setReleaseMonth(Integer releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Media{"
                + "title='" + title + '\''
                + ", filePath='" + filePath + '\''
                + ", imdbRating=" + imdbRating
                + ", runtime=" + runtime
                + '}';
    }
}
