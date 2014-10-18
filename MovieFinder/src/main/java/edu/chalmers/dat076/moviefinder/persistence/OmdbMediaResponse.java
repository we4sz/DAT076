/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class for handling restful media response from OMDB
 *
 * @author Carl Jansson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMediaResponse {
    
    // OMDB result for: http://www.omdbapi.com/?t=True%20Grit&y=1969
    /*
     {"Title":"True Grit",
     "Year":"1969"
     ,"Rated":"N/A"
     ,"Released":"11 Jun 1969",
     "Runtime":"128 min",
     "Genre":"Adventure, Western, Drama",
     "Director":"Henry Hathaway",
     "Writer":"Charles Portis (novel), Marguerite Roberts (screenplay)",
     "Actors":"John Wayne, Glen Campbell, Kim Darby, Jeremy Slate",
     "Plot":"A drunken, hard-nosed U.S. Marshal and a Texas Ranger help a stubborn young woman track down her father's murderer in Indian territory.",
     "Language":"English",
     "Country":"USA",
     "Awards":"Won 1 Oscar. Another 7 wins & 5 nominations.",
     "Poster":"http://ia.media-imdb.com/images/M/MV5BMTYwNTE3NDYzOV5BMl5BanBnXkFtZTcwNTU5MzY0MQ@@._V1_SX300.jpg",
     "Metascore":"N/A",
     "imdbRating":"7.4",
     "imdbVotes":"28,457",
     "imdbID":"tt0065126",
     "Type":"movie",
     "Response":"True"}
     */
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private int year;

    @JsonProperty("Rated")
    private String rated;

    @JsonProperty("Released")
    private String released;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Writer")
    private String writer;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Language")
    private String language;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Awards")
    private String awards;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("Metascore")
    private String metascore;

    @JsonProperty("imdbRating")
    private Double imdbRating;

    @JsonProperty("imdbVotes")
    private String imdbVotes;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Response")
    private String response;

    @JsonProperty("Error")
    private String error;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getPoster() {
        return poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "OmdbMovieResponse{"
                + "title='" + title + '\''
                + ", year='" + year + '\''
                + ", rated='" + rated + '\''
                + ", released='" + released + '\''
                + ", runtime='" + runtime + '\''
                + ", genre='" + genre + '\''
                + ", director='" + director + '\''
                + ", writer='" + writer + '\''
                + ", actors='" + actors + '\''
                + ", plot='" + plot + '\''
                + ", language='" + language + '\''
                + ", country='" + country + '\''
                + ", awards='" + awards + '\''
                + ", poster='" + poster + '\''
                + ", metascore='" + metascore + '\''
                + ", imdbRating='" + imdbRating + '\''
                + ", imdbVotes='" + imdbVotes + '\''
                + ", imdbID='" + imdbID + '\''
                + ", type='" + type + '\''
                + ", response='" + response + '\''
                + ", error='" + error + '\''
                + '}';
    }
}