/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/**
 *
 * @author Carl Jansson
 */
@Entity
public class Series extends AbstractEntity implements Serializable {
    
    
    //@OneToMany
    //@Embedded
    //private Episode episode;
    
    
    private String sID; // in TVDBSerie named id
    private String Status;
    private String poster;
    private String SeriesName;
    private String IMDB_ID;
    private double rating;
    
    protected Series(){
    }
    
    
    
    
    
}
