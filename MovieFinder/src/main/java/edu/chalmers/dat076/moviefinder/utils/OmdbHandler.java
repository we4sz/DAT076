/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.utils;

import edu.chalmers.dat076.moviefinder.persistence.OmdbMediaResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Carl Jansson
 */
public class OmdbHandler {
    
    RestTemplate restTemplate;
    
    public OmdbHandler(){
        init();
    }
    
    /*
    configure RestTemplate so it can reseive text/html as json
    */
    private void init(){
        restTemplate = new RestTemplate();
        
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        
        // OMDB returns json but with the HTTP Content-Type header set to "text/html",
        // so we have to add that as an accepted contet type for our jackson converter
        // or it will not trigger for the response
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("text", "html", AbstractJackson2HttpMessageConverter.DEFAULT_CHARSET));
        jacksonConverter.setSupportedMediaTypes(mediaTypes);
        
        // Add the custom jacksonConverter to the available messageConverters
        // of the restTemplate
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(jacksonConverter);
        restTemplate.setMessageConverters(messageConverters);
    }
    
    /**
     * 
     * @param title
     * @return the most recent movie with the best title match
     */
    public OmdbMediaResponse getOMDB(String title){
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?t=" + title, OmdbMediaResponse.class);
        return movie;
    }
    
    /**
     * 
     * @param title
     * @param year
     * @return the biggest thing from the year with the best title match
     */
    public OmdbMediaResponse getOMDB(String title, int year){
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?t=" + title + "&y=" + year, OmdbMediaResponse.class);
        return movie;
    }
    
    /**
     * 
     * @param imdbID
     * @return The omdb thing with the right imdbID
     */
    public OmdbMediaResponse getMoreOMDB(String imdbID){
        OmdbMediaResponse movie = restTemplate.getForObject("http://www.omdbapi.com/?i=" + imdbID, OmdbMediaResponse.class);
        return movie;
    }
    
}