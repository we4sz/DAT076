package edu.chalmers.dat076.moviefinder.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Peter on 2014-10-18.
 */

@Configuration
@Import({TestRepositoryConfig.class})
public class TestApplicationConfig {
}
