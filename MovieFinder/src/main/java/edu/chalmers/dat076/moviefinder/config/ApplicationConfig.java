package edu.chalmers.dat076.moviefinder.config;

import edu.chalmers.dat076.moviefinder.service.FileThreadService;
import edu.chalmers.dat076.moviefinder.utils.TitleParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Peter on 2014-10-17.
 */
@Configuration
@Import({RepositoryConfig.class, WebConfig.class})
public class ApplicationConfig {

    @Bean(name = "FileThreadService", destroyMethod = "destory")
    public FileThreadService fileThreadService() {
        return new FileThreadService();
    }

    @Bean
    public TitleParser titleParser() {
        return new TitleParser();
    }

}
