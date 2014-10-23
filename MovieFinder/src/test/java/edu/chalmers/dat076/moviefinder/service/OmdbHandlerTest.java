/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Carl Jansson
 */
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class OmdbHandlerTest {
/*
    @Configuration
    static class ContextConfiguration {
        @Bean
        public TraktHandler omdbHandler() {
            return new TraktHandler();
        }

        @Bean(name="JacksonHtmlRestTemplate")
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @Autowired
    private TraktHandler instance;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }


    @Test
    public void testGetOMDBInfoByTitle()  {
        mockServer.expect(requestTo("http://www.omdbapi.com/?t=TestTitle")).andRespond(withSuccess(
                "{\"Title\":\"TestTitle\", \"imdbID\":\"tt1403865\"}",
                MediaType.APPLICATION_JSON));

        OmdbMediaResponse response = instance.getByTitle("TestTitle");

        assertEquals(response.getTitle(), "TestTitle");
        assertEquals(response.getImdbID(), "tt1403865");

        mockServer.verify();
    }

    @Test
    public void testGetOMDBInfoByTitleYear() {
        mockServer.expect(requestTo("http://api.trakt.tv/movie/summary.json/a93c5b3dee40604933b1b8069883a844/" + "TestTitle".replace(" ", "-") + "-" + 2014)).andRespond(withSuccess(
                "{\"title\":\"TestTitle\", \"imdb_id\":\"tt1403865\"}",
                MediaType.APPLICATION_JSON));

        TraktMovieResponse response = instance.getByTitleYear("TestTitle", 2014);

        assertEquals(response.getTitle(), "TestTitle");
        assertEquals(response.getImdb_id(), "tt1403865");

        mockServer.verify();
    }
 
    public void testGetNonExistingMovie() {
        // This test simulates a request for a movie that doesn't exist on OMDB
        mockServer.expect(requestTo("http://www.omdbapi.com/?t=TestTitle")).andRespond(withSuccess(
                "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}",
                MediaType.APPLICATION_JSON));

        OmdbMediaResponse response = instance.getByTitle("TestTitle");
        assertNull(response);
    }
 
    @Test
    public void testGetMoreOMDB() {
        mockServer.expect(requestTo("http://www.omdbapi.com/?i=tt1403865")).andRespond(withSuccess(
                "{\"Title\":\"TestTitle\", \"imdbID\":\"tt1403865\"}",
                MediaType.APPLICATION_JSON));

        OmdbMediaResponse response = instance.getByImdbId("tt1403865");

        assertEquals(response.getTitle(), "TestTitle");
        assertEquals(response.getImdbID(), "tt1403865");

        mockServer.verify();
    }*/
}
