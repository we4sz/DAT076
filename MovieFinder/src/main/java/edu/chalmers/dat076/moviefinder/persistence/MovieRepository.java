/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A movie repository. Note that this interface is automagically implemented by
 * spring-data. The method names are used by spring to generate the queries, see
 * http://docs.spring.io/spring-data/jpa/docs/1.7.0.RELEASE/reference/html/#jpa.query-methods
 * for all available query methods.
 *
 * @author Peter
 */
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    public Page<Movie> findByTitleContaining(String name, Pageable pageable);

    public Movie findByFilePath(String filePath);

    public List<Movie> findAllByFilePathStartingWith(String filePath);
}
