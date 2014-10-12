/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder;

import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Tests the application config. This is done by first loading the application
 * config, then testing if we can get a known bean or not.
 * 
 * See https://github.com/spring-projects/spring-data-book/blob/master/jpa/src/test/java/com/oreilly/springdata/jpa/ApplicationConfigTest.java 
 * @author Peter
 */
public class ApplicationConfigTest {

	@Test
	public void bootstrapApp() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		assertThat(context, is(notNullValue()));
		assertThat(context.getBean(MovieRepository.class), is(notNullValue()));
	}

}