/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

/**
 *
 * @author John
 */
public class TVDBData {

    public TVDBData(TVDBSerie serie, TVDBEpisode episode) {
        this.serie = serie;
        this.episode = episode;
    }
    
    
    
    private TVDBSerie serie;
    private TVDBEpisode episode;

    public TVDBEpisode getEpisode() {
        return episode;
    }

    public TVDBSerie getSerie() {
        return serie;
    }
    
    
    
    
    
}
