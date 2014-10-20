/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.controller;

import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author John
 */

@Controller
@RequestMapping("api/files")
public class FileController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Page<Movie> listMovies() {
        return movieRepository.findAll(new PageRequest(0, 25));
    }
}
