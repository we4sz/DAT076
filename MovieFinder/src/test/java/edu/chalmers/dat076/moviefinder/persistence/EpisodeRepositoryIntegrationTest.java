/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.test.AbstractIntegrationTest;
import edu.chalmers.dat076.moviefinder.test.config.TestApplicationConfig;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
public class EpisodeRepositoryIntegrationTest extends AbstractIntegrationTest {
    
    @Autowired
    EpisodeRepository repository;

    @Test
    public void createEpisode() {
        Episode episode = new Episode("EpisodeTest2", "EpisodeTest2.avi", 1, 1);
        repository.save(episode);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createMultipleEpisodesWithSameFilePath() {
        // File path is unique, this should not be possible.
        Episode m1 = new Episode("EpisodeTest2", "EpisodeTest2.avi", 1, 1);
        Episode m2 = new Episode("EpisodeTest3", "EpisodeTest2.avi", 1, 2);
        repository.save(m1);
        repository.save(m2);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void findByTitleContaining() {
        Pageable pageable = new PageRequest(0, 1);
        Page<Episode> page = repository.findByTitleContaining("testEpisode", pageable);
        assertEquals("testEpisode1", page.getContent().get(0).getTitle());
        assertThat(page.getContent(), hasSize(1));
        assertThat(page, Matchers.<Episode> hasItems(hasProperty("title", is("testEpisode1"))));
    }

    @Test
    public void findByFilePath() {
        Episode e = repository.findByFilePath("testEpisode1.avi");
        assertThat(e.getTitle(), is("testEpisode1"));
    }
    
}
