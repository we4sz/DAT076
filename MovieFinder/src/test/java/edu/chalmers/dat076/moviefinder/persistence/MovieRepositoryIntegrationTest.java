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
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
 * @author Peter
 */
@ContextConfiguration(classes = TestApplicationConfig.class)
public class MovieRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    MovieRepository repository;
    
    @Test
    public void createMovie() {
        Movie movie = new Movie("MovieTest2", "movieTest2.avi");
        repository.save(movie);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createMultipleMoviesWithSameFilePath() {
        // File path is unique, this should not be possible.
        Movie m1 = new Movie("MovieTest2", "movieTest2.avi");
        Movie m2 = new Movie("MovieTest3", "movieTest2.avi");
        repository.save(m1);
        repository.save(m2);
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
