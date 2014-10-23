/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Peter, Carl Jansson
 */
@ContextConfiguration(classes = TestApplicationConfig.class)
public class SerieRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    SeriesRepository repository;

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
        Page<Series> page = repository.findBySeriesNameContaining("testSeries", pageable);
        assertEquals("testSeries1", page.getContent().get(0).getSeriesName());
        assertThat(page.getContent(), hasSize(1));
        assertThat(page, Matchers.<Series>hasItems(hasProperty("seriesName", is("testSeries1"))));
    }

    @Test
    public void findBySID() {
        Series s = repository.findBySID("testSeriesID");
        assertThat(s.getSeriesName(), is("testSeries1"));

        s = repository.findBySID("NonExistingSID");
        assertTrue(s == null);
    }

    @Test
    public void saveAndGetSeriesWithEpisodes() {
        Series serie = new Series("EpisodeSeriesTest", "estID");
        List<Episode> eList = new ArrayList<>();
        eList.add(new Episode("title", "path1", 1, 1));
        eList.add(new Episode("title", "path2", 1, 2));
        serie.setEpisodes(eList);
        repository.save(serie);

        Series serieRe = repository.findBySID("estID");
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

        Series serieRe = repository.findBySID("estID");
        List<Episode> reEp = serieRe.getEpisodes();
        assertThat(reEp, hasSize(2));

        serieRe.addEpisodes(new Episode("title", "path3", 1, 3));
        repository.save(serieRe);

        serieRe = repository.findBySID("estID");
        reEp = serieRe.getEpisodes();
        assertThat(reEp, hasSize(3));

    }
}
