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
public class TraktEpisodeResponse implements TraktResponse{

    private TraktEpisode episode;
    private TraktShow show;

    public TraktEpisode getEpisode() {
        return episode;
    }

    public void setEpisode(TraktEpisode episode) {
        this.episode = episode;
    }

    public TraktShow getShow() {
        return show;
    }

    public void setShow(TraktShow show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "TraktEpisodeResponse{" + "episode=" + episode + ", show=" + show + '}';
    }

    
    
}
