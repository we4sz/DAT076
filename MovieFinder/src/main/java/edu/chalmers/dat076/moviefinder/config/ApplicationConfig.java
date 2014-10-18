package edu.chalmers.dat076.moviefinder.config;

import edu.chalmers.dat076.moviefinder.service.FileThreadService;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 2014-10-17.
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

    @Bean(name = "JacksonHtmlRestTemplate")
    public RestTemplate jacksonHtmlRestOperations() {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();

        // OMDB returns json but with the HTTP Content-Type header set to "text/html",
        // so we have to add that as an accepted contet type for our jackson converter
        // or it will not trigger for the response
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("text", "html", AbstractJackson2HttpMessageConverter.DEFAULT_CHARSET));
        jacksonConverter.setSupportedMediaTypes(mediaTypes);

        // Add the custom jacksonConverter to the available messageConverters
        // of the restTemplate
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(jacksonConverter);
        restTemplate.setMessageConverters(messageConverters);


        return restTemplate;
    }
}
