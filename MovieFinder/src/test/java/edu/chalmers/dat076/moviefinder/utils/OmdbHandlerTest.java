/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.utils;

import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Carl Jansson
 */
public class OmdbHandlerTest {
    
    private static OmdbHandler instance;
    
    public OmdbHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new OmdbHandler();
    }
        
    /**
     * Test of getOMDB method, of class OMDBinfo.
     */
    @Test
    public void testGetOMDB() {
        String title;
        int year;
        OmdbMediaResponse result;
        
        title = "True Grit";
        result = instance.getOMDB(title);
        assertEquals("tt1403865", result.getImdbID());
        
        year = 2010;
        result = instance.getOMDB(title, year);
        assertEquals("tt1403865", result.getImdbID());
        
        year = 1969;
        result = instance.getOMDB(title, year);
        assertEquals("tt0065126", result.getImdbID());
        
        title = "Star Wars: Episode IV - A New Hope";
        result = instance.getOMDB(title);
        assertEquals("tt0076759", result.getImdbID());
        
        title = "Star Wars Episode IV A New Hope";
        result = instance.getOMDB(title);
        assertEquals("tt0076759", result.getImdbID());
        
        // Parser could remove the '-' with 3 ' ' as result
        title = "Star Wars: Episode IV   A New Hope";
        result = instance.getOMDB(title);
        assertEquals("tt0076759", result.getImdbID());
        
        title = "Star Wars";
        year = 1977;
        result = instance.getOMDB(title, year);
        assertEquals("tt0076759", result.getImdbID());
        
        title = "Star Wars Episode IV";
        result = instance.getOMDB(title, year);
        assertEquals("tt0076759", result.getImdbID());
        
        title = "absolotely nonsense asdfasdfs";
        try {
            result = instance.getOMDB(title, year);
            fail("exception not cast by: absolotely nonsense asdfasdfs");
        } catch (NullPointerException e){
            // Deafault not failing
        }
        
    }
    
    @Test
    public void testGetMoreOMDB() {
        String imdbID;
        OmdbMediaResponse result;
        
        imdbID = "tt1403865";
        result = instance.getMoreOMDB(imdbID);
        assertEquals("True Grit", result.getTitle());
        assertEquals(2010, result.getYear());
        
        imdbID = "tt0065126";
        result = instance.getMoreOMDB(imdbID);
        assertEquals("True Grit", result.getTitle());
        assertEquals(1969, result.getYear());
        
    }
}
