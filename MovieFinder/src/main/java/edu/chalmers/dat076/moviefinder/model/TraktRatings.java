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
public class TraktRatings {

    private int percentage;
    private int votes;
    private int loved;
    private int hated;

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getLoved() {
        return loved;
    }

    public void setLoved(int loved) {
        this.loved = loved;
    }

    public int getHated() {
        return hated;
    }

    public void setHated(int hated) {
        this.hated = hated;
    }

    @Override
    public String toString() {
        return "TraktRatings{" + "percentage=" + percentage + ", votes=" + votes + ", loved=" + loved + ", hated=" + hated + '}';
    }

    
}
