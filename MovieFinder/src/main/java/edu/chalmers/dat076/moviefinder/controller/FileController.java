
package edu.chalmers.dat076.moviefinder.controller;

import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import edu.chalmers.dat076.moviefinder.persistence.MovieSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for the api/files endpoint.
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
    Page<Movie> listMovies(
            @RequestParam(value = "imdbRating", required = false) Double imdbRating,
            @RequestParam(value = "runtime", required = false) Integer runtime
    ) {
        PageRequest pageRequest = new PageRequest(0, 25);
        
        Specifications<Movie> filter = where(null);
                
        if (imdbRating != null){
            filter = filter.and(MovieSpecs.hasImdbRatingAbove(imdbRating));
        }
        if (runtime != null){
            filter = filter.and(MovieSpecs.hasRuntimeAbove(runtime));
        }
        return movieRepository.findAll(filter, pageRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Movie getMovieById(@PathVariable long id) {
        return movieRepository.findOne(id);
    }
    
}
