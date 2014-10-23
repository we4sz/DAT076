/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktActor;
import edu.chalmers.dat076.moviefinder.model.TraktImages;
import java.io.Serializable;
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
 *
 * @author Carl Jansson
 */
@MappedSuperclass
public abstract class Media extends AbstractEntity implements Serializable {
    
    
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
        
    protected Media(){
    }
    
    public Media(String filePath) {
        this.filePath = filePath;
    }
    
    public Media(String title, String filePath) {
        this.filePath = filePath;
        this.title = title;
    }

    protected List<Actor> toActors(List<TraktActor> actors) {
        List<Actor> _a = new LinkedList<>();
        for (TraktActor a : actors) {
            _a.add(new Actor(a));
        }
        return _a;
    }

    protected String getImage(TraktImages i) {
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
        return "Movie{"
                + "title='" + title + '\''
                + ", filePath='" + filePath + '\''
                + ", imdbRating=" + imdbRating
                + ", runtime=" + runtime
                + '}';
    }
}
