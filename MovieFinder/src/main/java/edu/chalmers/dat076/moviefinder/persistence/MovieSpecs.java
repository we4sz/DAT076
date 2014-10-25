/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Carl Jansson
 */
public class MovieSpecs {
    
    /**
     * 
     * @param imdbRating
     * @return returns all movies with a rating equal to or above imdbRating.
     */
    public static Specification<Movie> hasImdbRatingAbove(final double imdbRating){
        return new Specification<Movie>(){
            @Override
            public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get(Movie_.imdbRating), imdbRating);
            }
    };
    }
    
    /**
     * 
     * @param runtime
     * @return All movies with an runtime equal to or above runtime.
     */
    public static Specification<Movie> hasRuntimeAbove(final int runtime){
        return new Specification<Movie>(){
            @Override
            public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get(Movie_.runtime), runtime);
            }
    };
    }
    
    /**
     * 
     * @param releaseYear
     * @return All movies with the release year releaseYear.
     */
    public static Specification<Movie> hasReleaseYear(final int releaseYear){
        return new Specification<Movie>(){
            @Override
            public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(Movie_.releaseYear), releaseYear);
            }
    };
    }
    
    
}
