/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.model;

/**
 * A class for handling and storing series episodes.
 * 
 * @author Carl Jansson
 */
public class SeriesEpisode extends AbstractMedia {
    
    private int season;
    private int episode;
    
    public SeriesEpisode(){
        super();
    }
    
    public int getSeason(){
        return season;
    }
    
    public void setSeason(int s){
        this.season=s;
    }
    
    public int getEpisode(){
        return episode;
    }
    
    public void setEpisode(int e){
        this.episode=e;
    }
    
}
