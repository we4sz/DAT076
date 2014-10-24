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
public class TraktDirector {

    private String name;
    private TraktImages images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TraktImages getImages() {
        return images;
    }

    public void setImages(TraktImages images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "TraktDirector{" + "name=" + name + ", images=" + images + '}';
    }
    
    

}
