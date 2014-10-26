/* 
 * The MIT License
 *
 * Copyright 2014 Anton, Carl, John, Peter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
    
    /**
     * Creates a new TemporaryMedia that is set to be a movie.
     */
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
