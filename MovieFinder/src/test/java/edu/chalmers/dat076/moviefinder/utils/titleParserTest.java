/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.utils;


import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
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
        instance = new TitleParser();
    }
    
    @Test
    public void testParseMedia(){
        
        // Movie Tests
        TemporaryMedia t = instance.parseMedia("[]hej");
        assertEquals("hej", t.getName());
        assertTrue(t.IsMovie());
        t = instance.parseMedia("[abcd()-/123]hej!");
        assertEquals("hej!", t.getName());
        assertTrue(t.IsMovie());
        t = instance.parseMedia("Min.Speciella.Film.2007.1080p.mkv");
        assertEquals("Min Speciella Film", t.getName());
        assertEquals(2007, t.getYear());
        assertTrue(t.IsMovie());
        t = instance.parseMedia("[av mig]Min Speciella Film 2006 720p.mkv");
        assertEquals("Min Speciella Film", t.getName());
        assertEquals(2006, t.getYear());
        assertTrue(t.IsMovie());
        t = instance.parseMedia("Min.Speciella.Film.[2005].mkv");
        assertEquals("Min Speciella Film", t.getName());
        assertEquals(2005, t.getYear());
        assertTrue(t.IsMovie());
        t = instance.parseMedia("[av mig]Min.Speciella.Film-2004_[1080p].mkv");
        assertEquals("Min Speciella Film", t.getName());
        assertEquals(2004, t.getYear());
        assertTrue(t.IsMovie());
        t = instance.parseMedia("1987.Min.Speciella.Film-2003_(slutligen)1080p[aaa].mkv");
        assertEquals("1987 Min Speciella Film", t.getName());
        assertEquals(2003, t.getYear());
        assertTrue(t.IsMovie());
        
        // Series tests
        t = instance.parseMedia("min serie! Season1Episode2");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 1, t.getSeason());
        assertEquals( 2, t.getEpisode());
        t = instance.parseMedia("[blaaa]_min.serie!_Season13 Episode24");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 13, t.getSeason());
        assertEquals( 24, t.getEpisode());
        t = instance.parseMedia("min serie! Season 05 Episode 06");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 5, t.getSeason());
        assertEquals( 6, t.getEpisode());
        t = instance.parseMedia("min serie! Season1Episode2");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 1, t.getSeason());
        assertEquals( 2, t.getEpisode());
        
        t = instance.parseMedia("min serie! S1E2");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 1, t.getSeason());
        assertEquals( 2, t.getEpisode());
        t = instance.parseMedia("min serie! S01E02.mp4");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 1, t.getSeason());
        assertEquals( 2, t.getEpisode());
        t = instance.parseMedia("min.serie![2012]-S11E12.1080p.mkv");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 11, t.getSeason());
        assertEquals( 12, t.getEpisode());
        assertEquals(2012, t.getYear());
        
        t = instance.parseMedia("min.serie![2012]-1x2.1080p.mkv");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 1, t.getSeason());
        assertEquals( 2, t.getEpisode());
        assertEquals(2012, t.getYear());
        t = instance.parseMedia("min.serie!.2012-01x02.1080p.mkv");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 1, t.getSeason());
        assertEquals( 2, t.getEpisode());
        assertEquals(2012, t.getYear());
        t = instance.parseMedia("min.serie!(2012)-11x23.1080p.mkv");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 11, t.getSeason());
        assertEquals( 23, t.getEpisode());
        t = instance.parseMedia("min.serie![2012]-11x23.2012-1080p.mkv");
        assertEquals("min serie!", t.getName());
        assertTrue( !t.IsMovie());
        assertEquals( 11, t.getSeason());
        assertEquals( 23, t.getEpisode());
    }

    
    @Test
    public void testRemoveUntil(){
        
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
        
        StringBuilder sb = new StringBuilder("1asd");
        int e = instance.getNextNumber(sb);
        assertTrue(e==1);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asd1");
        e = instance.getNextNumber(sb);
        assertTrue(e==1);
        assertEquals("",sb.toString());
        sb = new StringBuilder(" 01asd");
        e = instance.getNextNumber(sb);
        assertTrue(e==1);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asdfs01asd");
        e = instance.getNextNumber(sb);
        assertTrue(e==1);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asd01");
        e = instance.getNextNumber(sb);
        assertTrue(e==1);
        assertEquals("",sb.toString());
        sb = new StringBuilder(" 23asd");
        e = instance.getNextNumber(sb);
        assertTrue(e==23);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asd23");
        e = instance.getNextNumber(sb);
        assertTrue(e==23);
        assertEquals("",sb.toString());
        sb = new StringBuilder("asdfs2343asd");
        e = instance.getNextNumber(sb);
        assertTrue(e==2343);
        assertEquals("asd",sb.toString());
        sb = new StringBuilder("asdfsasd");
        e = instance.getNextNumber(sb);
        assertTrue(e==-1);
        assertEquals("asdfsasd",sb.toString());
    }
        
    
    @Test
    public void testGetEpisodePotential(){
        
        StringBuilder sb = new StringBuilder("Season1Episode2");
        assertTrue(instance.getEpisodePotential(sb));
        
        sb = new StringBuilder("Season01Episode02");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("Season11Episode22");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("Season3 Episode04");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("Season13 Episode24");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("Season 05 Episode 06");
        assertTrue(instance.getEpisodePotential(sb));
        
        sb = new StringBuilder("S1E2");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("s01e02");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("s11e22");
        assertTrue(instance.getEpisodePotential(sb));
        
        sb = new StringBuilder("1x2");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("1x02");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("01x02");
        assertTrue(instance.getEpisodePotential(sb));
        sb = new StringBuilder("11x22");
        assertTrue(instance.getEpisodePotential(sb));
    }
    
    
    @Test
    public void testGetEpisodeInfo(){
        
        StringBuilder sb = new StringBuilder("Season1Episode2");
        TemporaryMedia t = instance.getEpisodeInfo(sb);
        assertTrue(t.getSeason()==1 && t.getEpisode()==2);
        assertEquals("1 2", t.getSeason() + " " + t.getEpisode());
        
        sb = new StringBuilder("Season01Episode02");
        t = instance.getEpisodeInfo(sb);
        assertTrue(t.getSeason()==1 && t.getEpisode()==2);
        sb = new StringBuilder("Season11Episode22");
        t = instance.getEpisodeInfo(sb);
        assertTrue(t.getSeason()==11 && t.getEpisode()==22);
        sb = new StringBuilder("Season3 Episode04");
        t = instance.getEpisodeInfo(sb);
        assertTrue(t.getSeason()==3 && t.getEpisode()==4);
        sb = new StringBuilder("Season13 Episode24");
        t = instance.getEpisodeInfo(sb);
        assertTrue(t.getSeason()==13 && t.getEpisode()==24);
        sb = new StringBuilder("Season 05 Episode 06");
        t = instance.getEpisodeInfo(sb);
        assertTrue(t.getSeason()==5 && t.getEpisode()==6);
        
        sb = new StringBuilder("S1E2");
        t = instance.getEpisodeInfo(sb);
        assertEquals("1 2", t.getSeason() + " " + t.getEpisode());
        assertTrue(t.getSeason()==1 && t.getEpisode()==2);
        sb = new StringBuilder("s01e02");
        t = instance.getEpisodeInfo(sb);
        assertEquals("1 2", t.getSeason() + " " + t.getEpisode());
        sb = new StringBuilder("s11e22");
        t = instance.getEpisodeInfo(sb);
        assertEquals("11 22", t.getSeason() + " " + t.getEpisode());
        
        sb = new StringBuilder("1x2");
        t = instance.getEpisodeInfo(sb);
        assertEquals("1 2", t.getSeason() + " " + t.getEpisode());
        sb = new StringBuilder("1x02");
        t = instance.getEpisodeInfo(sb);
        assertEquals("1 2", t.getSeason() + " " + t.getEpisode());
        sb = new StringBuilder("01x02");
        t = instance.getEpisodeInfo(sb);
        assertEquals("1 2", t.getSeason() + " " + t.getEpisode());
        sb = new StringBuilder("11x22");
        t = instance.getEpisodeInfo(sb);
        assertEquals("11 22", t.getSeason() + " " + t.getEpisode());
    }
    
}
