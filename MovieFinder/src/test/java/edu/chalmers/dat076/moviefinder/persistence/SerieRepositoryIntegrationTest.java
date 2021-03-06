/* 
 * The MIT License
 *
 * Copyright 2014 Anton, Carl, John, Peter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.test.AbstractIntegrationTest;
import edu.chalmers.dat076.moviefinder.test.config.TestApplicationConfig;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Carl Jansson
 */
@ContextConfiguration(classes = TestApplicationConfig.class)
public class SerieRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    SeriesRepository repository;

    // Used to check that episodes are realy saved when added into series.
    @Autowired
    EpisodeRepository eRepository;

    @Test
    public void createSeries() {
        Series serie = new Series("SeriesTest2", "SeriesTest2id");
        repository.save(serie);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createMultipleSeriesWithSameSeriesID() {
        // sID unique, this should not be possible.
        Series s1 = new Series("SeriesTest2", "SeriesTest2id");
        Series s2 = new Series("SeriesTest3", "SeriesTest2id");
        repository.save(s1);
        repository.save(s2);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void findBySeriesNameContaining() {
        Pageable pageable = new PageRequest(0, 1);
        Page<Series> page = repository.findByTitleContaining("testSeries", pageable);
        assertEquals("testSeries1", page.getContent().get(0).getTitle());
        assertThat(page.getContent(), hasSize(1));
        assertThat(page, Matchers.<Series>hasItems(hasProperty("title", is("testSeries1"))));
    }

    @Test
    public void findBySID() {
        Series s = repository.findByImdbId("testSeriesID");
        assertThat(s.getTitle(), is("testSeries1"));

        s = repository.findByImdbId("NonExistingSID");
        assertTrue(s == null);
    }
    
    
    
    
     /**
      * Tests for series - episode relations
      */
    
     @Test
     public void saveAndGetSeriesWithEpisodes() {
     Series serie = new Series("EpisodeSeriesTest", "estID");
     List<Episode> eList = new ArrayList<>();
     eList.add(new Episode("title", "path1", 1, 1));
     eList.add(new Episode("title", "path2", 1, 2));
     serie.setEpisodes(eList);
     repository.save(serie);

     Series serieRe = repository.findByImdbId("estID");
     List<Episode> reEp = serieRe.getEpisodes();
     assertThat(reEp, hasSize(2));
     assertEquals("title", reEp.get(0).getTitle());
     }

     @Test
     public void saveGetAndUpdateSeriesWithEpisodes() {
     Series serie = new Series("EpisodeSeriesTest", "estID");
     List<Episode> eList = new ArrayList<>();
     eList.add(new Episode("title", "path1", 1, 1));
     eList.add(new Episode("title", "path2", 1, 2));
     serie.setEpisodes(eList);
     repository.save(serie);

     Series serieRe = repository.findByImdbId("estID");
     List<Episode> reEp = serieRe.getEpisodes();
     assertThat(reEp, hasSize(2));

     serieRe.getEpisodes().add(new Episode("title", "path3", 1, 3));
     repository.save(serieRe);

     serieRe = repository.findByImdbId("estID");
     reEp = serieRe.getEpisodes();
     assertThat(reEp, hasSize(3));
        
     Episode e = eRepository.findByFilePath("path1");
     assertEquals("title", e.getTitle());
        
     e = eRepository.findByFilePath("path2");
     assertEquals("title", e.getTitle());
     }
}
