/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.model;

/**
 * Class for temporarily saving movie and or series information.
 *
 * @author Carl Jansson
 */
public class TemporaryMedia {
    
    private String name;
    // If it isn't a movie it must be a series
    private boolean isMovie;
    private int year;
    private int season;
    private int episode;
    
    
    
    public TemporaryMedia(){
        this.isMovie = true;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setIsMovie(boolean isMovie){
        this.isMovie=isMovie;
    }
    public void setSeason(int season){
        this.season=season;
    }
    public void setEpisode(int episode){
        this.episode=episode;
    }
    public void setYear(int year){
        this.year = year;
    }
    
    public String getName(){
        return name;
    }
    /**
     * If it is not a movie it must be an episode!
     * @return true if it is a movie
     */
    public boolean IsMovie(){
        return isMovie;
    }
    public int getSeason(){
        return season;
    }
    public int getEpisode(){
        return episode;
    }
    public int getYear(){
        return year;
    }


    @Override
    public String toString() {
        return "TemporaryMedia{" +
                "name='" + name + '\'' +
                ", isMovie=" + isMovie +
                ", year=" + year +
                ", season=" + season +
                ", episode=" + episode +
                '}';
    }
}
