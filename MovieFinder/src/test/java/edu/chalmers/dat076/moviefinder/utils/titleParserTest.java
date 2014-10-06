/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carl Jansson
 */
public class titleParserTest {

    TitleParser instance;
    
    public titleParserTest() {
    }

    @Before
    public void setUp() {
        /* This method is executed before each test. It is used to prepare 
        the test environment (e.g., read input data, initialize the class).*/
        
        instance = new TitleParser();
    }


    @Test
    public void testParseTitle(){
        System.out.println("parseTitle");
        String s = instance.parseTitle("[]hej");
        assertEquals("hej", s);
        s = instance.parseTitle("[abcd()-/123]hej!");
        assertEquals("hej!", s);
        s = instance.parseTitle("Min.Speciella.Film.2007.1080p.mkv");
        assertEquals("Min Speciella Film 2007", s);
        s = instance.parseTitle("[av mig]Min Speciella Film 2006 720p.mkv");
        assertEquals("Min Speciella Film 2006", s);
        s = instance.parseTitle("Min.Speciella.Film.2005.mkv");
        assertEquals("Min Speciella Film 2005", s);
        s = instance.parseTitle("[av mig]Min.Speciella.Film-2004_[1080p].mkv");
        assertEquals("Min Speciella Film 2004", s);
        s = instance.parseTitle("Min.Speciella.Film.2003.(slutligen)1080p[aaa].mkv");
        assertEquals("Min Speciella Film 2003", s);
    }
    
    
    @Test
    public void testRemoveFormating(){
        System.out.println("removeFormating");
        StringBuilder sb = new StringBuilder("-min.egna-speciella_film!-_. Perfect.");
        instance.removeFormating(sb);
        assertEquals(" min egna speciella film!    Perfect ", sb.toString());
    }
    
    
    @Test
    public void testRemoveUntil(){
        System.out.println("removeUntil");
        StringBuilder sb = new StringBuilder("[]hej");
        //assertTrue(sb.charAt(0) == '[');
        instance.removeUntil(sb, 0, ']');
        assertEquals("hej", sb.toString());
        sb.append("[abcd()-/123] ok!");
        //assertTrue(sb.charAt(3) == '[');
        instance.removeUntil(sb, 3, ']');
        assertEquals("hej ok!", sb.toString());
        sb.append("[What if last char is bracket?]");
        //assertTrue(sb.charAt(7) == '[');
        instance.removeUntil(sb, 7, ']');
        assertEquals("hej ok!", sb.toString());
    }
    
}
