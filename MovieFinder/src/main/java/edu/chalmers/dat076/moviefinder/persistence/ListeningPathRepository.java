/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author John
 */
public interface ListeningPathRepository extends PagingAndSortingRepository<ListeningPath, Long>, JpaSpecificationExecutor<ListeningPath>{
    
    public ListeningPath findByListeningPath(String listeningPath);
}
