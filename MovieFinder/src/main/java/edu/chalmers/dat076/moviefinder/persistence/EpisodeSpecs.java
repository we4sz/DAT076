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
public class EpisodeSpecs {
    
    /**
     * 
     * @param imdbRating
     * @return All Episodes with a rating equal to or above imdbRating
     */
    public static Specification<Episode> hasImdbRatingAbove(final double imdbRating){
        return new Specification<Episode>(){
            @Override
            public Predicate toPredicate(Root<Episode> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get(Episode_.imdbRating), imdbRating);
            }
    };
    }
    
    /**
     * 
     * @param releaseYear
     * @return All Episodes released the year releaseYear
     */
    public static Specification<Episode> hasReleaseYear(final int releaseYear){
        return new Specification<Episode>(){
            @Override
            public Predicate toPredicate(Root<Episode> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(Episode_.releaseYear), releaseYear);
            }
    };
    }
    
    
}
