/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * A class for getting information about a movie from OMDB.
 * @author Carl Jansson, Peter Eliasson
 */
@Service
public class OmdbHandler {

    @Autowired
    @Qualifier("JacksonHtmlRestTemplate")
    RestTemplate restTemplate;

    /**
     * Looks up a movie by its title.
     * @param title The title of the movie.
     * @return the most recent movie with the best title match, or null if no movie could be found.
     */
    public OmdbMediaResponse getByTitle(String title) throws NullPointerException{
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?t=" + title, OmdbMediaResponse.class);
        if ( movie.getTitle() == null ) {
            return null;
        }
        return movie;
    }
    
    /**
     * Looks up a movie by its title and year.
     * @param title The title of the movie.
     * @param year The year the movie was released.
     * @return the biggest thing from the year with the best title match, or null if no movie could be found.
     */
    public OmdbMediaResponse getByTitleYear(String title, int year) throws NullPointerException{
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?t=" + title + "&y=" + year, OmdbMediaResponse.class);
        if ( movie.getTitle() == null ) {
            return null;
        }
        return movie;
    }
    
    /**
     * Looks up a movie by its imdb id.
     * @param imdbID The movie's imdb id.
     * @return The movie with the matching imdbID, or null if non could be found.
     */
    public OmdbMediaResponse getByImdbId(String imdbID) throws NullPointerException{
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?i=" + imdbID, OmdbMediaResponse.class);
        if ( movie.getTitle() == null ) {
            return null;
        }
        return movie;
    }
    
}