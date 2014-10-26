package edu.chalmers.dat076.moviefinder.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;


/**
 * Base configuration class for the Spring application.
 * Includes other needed configuration classes and defines
 * project-specific beans.
 */
@Configuration
@Import({RepositoryConfig.class, WebConfig.class})
@ComponentScan(basePackages = {"edu.chalmers.dat076.moviefinder.service"})
public class ApplicationConfig {

    @Bean
    PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}
