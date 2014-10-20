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
 *
 * @author Carl Jansson, Peter Eliasson
 */
@Service
public class OmdbHandler {

    @Autowired
    @Qualifier("JacksonHtmlRestTemplate")
    RestTemplate restTemplate;

    /**
     * 
     * @param title
     * @return the most recent movie with the best title match
     */
    public OmdbMediaResponse getOMDB(String title) throws NullPointerException{
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?t=" + title, OmdbMediaResponse.class);
        if ( movie.getTitle() == null ) {
            throw new NullPointerException();
        }
        return movie;
    }
    
    /**
     * 
     * @param title
     * @param year
     * @return the biggest thing from the year with the best title match
     */
    public OmdbMediaResponse getOMDB(String title, int year) throws NullPointerException{
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?t=" + title + "&y=" + year, OmdbMediaResponse.class);
        if ( movie.getTitle() == null ) {
            throw new NullPointerException();
        }
        return movie;
    }
    
    /**
     * 
     * @param imdbID
     * @return The omdb thing with the right imdbID
     */
    public OmdbMediaResponse getMoreOMDB(String imdbID) throws NullPointerException{
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?i=" + imdbID, OmdbMediaResponse.class);
        if ( movie.getTitle() == null ) {
            throw new NullPointerException();
        }
        return movie;
    }
    
}