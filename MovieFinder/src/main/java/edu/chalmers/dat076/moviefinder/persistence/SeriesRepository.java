/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Carl Jansson
 */
public interface SeriesRepository extends PagingAndSortingRepository<Series, Long>, JpaSpecificationExecutor<Series> {
    public Page<Series> findBySeriesNameContaining(String SeriesName, Pageable pageable);

    public Series findBySID(String sID);

}
