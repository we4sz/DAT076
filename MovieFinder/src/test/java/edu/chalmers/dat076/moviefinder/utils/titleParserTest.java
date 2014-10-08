/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.utils;

import edu.chalmers.dat076.moviefinder.utils.TitleParser.Episode;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    
    @Test
    public void testReturnNextNumber(){
        System.out.println("returnNextNumber");
        StringBuilder sb = new StringBuilder("1asd");
        int e = instance.returnNextNumber(sb);
        assertTrue(e==1);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asd1");
        e = instance.returnNextNumber(sb);
        assertTrue(e==1);
        assertEquals("",sb.toString());
        sb = new StringBuilder(" 01asd");
        e = instance.returnNextNumber(sb);
        assertTrue(e==1);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asdfs01asd");
        e = instance.returnNextNumber(sb);
        assertTrue(e==1);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asd01");
        e = instance.returnNextNumber(sb);
        assertTrue(e==1);
        assertEquals("",sb.toString());
        sb = new StringBuilder(" 23asd");
        e = instance.returnNextNumber(sb);
        assertTrue(e==23);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asd23");
        e = instance.returnNextNumber(sb);
        assertTrue(e==23);
        assertEquals("",sb.toString());
        sb = new StringBuilder("asdfs2343asd");
        e = instance.returnNextNumber(sb);
        assertTrue(e==2343);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asdfsasd");
        e = instance.returnNextNumber(sb);
        assertTrue(e==-1);
        assertEquals("asdfsasd",sb.toString());
    }
        
    
    
    @Test
    public void testGetEpisodeInfo(){
        System.out.println("getEpisodeInfo");
        StringBuilder sb = new StringBuilder("Season1Episode2");
        Episode e = instance.getEpisodeInfo(sb);
        assertTrue(e.getSeason()==1 && e.getEpisode()==2);
        assertEquals("1 2", e.getSeason() + " " + e.getEpisode());
        
        sb = new StringBuilder("Season01Episode02");
        e = instance.getEpisodeInfo(sb);
        assertTrue(e.getSeason()==1 && e.getEpisode()==2);
        
        sb = new StringBuilder("Season11Episode22");
        e = instance.getEpisodeInfo(sb);
        assertTrue(e.getSeason()==11 && e.getEpisode()==22);
        
        sb = new StringBuilder("Season3 Episode04");
        e = instance.getEpisodeInfo(sb);
        assertTrue(e.getSeason()==3 && e.getEpisode()==4);
        
        sb = new StringBuilder("Season13 Episode24");
        e = instance.getEpisodeInfo(sb);
        assertTrue(e.getSeason()==13 && e.getEpisode()==24);
        
        sb = new StringBuilder("Season 05 Episode 06");
        e = instance.getEpisodeInfo(sb);
        assertTrue(e.getSeason()==5 && e.getEpisode()==6);
        
        /*sb = new StringBuilder("S1E2");
        sb = new StringBuilder("s01e02");
        sb = new StringBuilder("s11e22");
        
        sb = new StringBuilder("1x2");
        sb = new StringBuilder("1x02");
        sb = new StringBuilder("01x02");
        sb = new StringBuilder("11x22");
        */
        
    }
    
}
