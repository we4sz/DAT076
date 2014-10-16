/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/**
 *
 * @author Peter
 */
@Configuration
@EnableWebMvcSecurity
@ComponentScan("edu.chalmers.dat076.moviefinder")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.headers()
                .cacheControl()
                .contentTypeOptions()
                .frameOptions()
                .xssProtection()
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"))
                .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy", "script-src 'self'"));
    }
}
