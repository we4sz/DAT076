/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.AbstractIntegrationTest;
import edu.chalmers.dat076.moviefinder.TestRepositoryConfig;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Peter
 */
@ContextConfiguration(classes = TestRepositoryConfig.class)
public class MovieRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    MovieRepository repository;

    @Test
    public void createMovie() {
        Movie movie = new Movie("MovieTest2", "movieTest2.avi");
        repository.save(movie);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void findByTitleContaining() {
        Pageable pageable = new PageRequest(0, 1);
        Page<Movie> page = repository.findByTitleContaining("testMovie", pageable);
        
        assertThat(page.getContent(), hasSize(1));
        assertThat(page, Matchers.<Movie> hasItems(hasProperty("title", is("testMovie1"))));
    }

    @Test
    public void findByFileName() {
        Movie m = repository.findByFilePath("testMovie1.avi");
        assertThat(m.getTitle(), is("testMovie1"));
    }
}
