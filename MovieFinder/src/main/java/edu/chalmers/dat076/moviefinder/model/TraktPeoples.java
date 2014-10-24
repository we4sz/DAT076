/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import java.util.List;

/**
 *
 * @author John
 */
public class TraktPeoples {

    private List<TraktProducer> producers;
    private List<TraktActor> actors;
    private List<TraktWriter> writers;
    private List<TraktDirector> directors;

    public void setActors(List<TraktActor> actors) {
        this.actors = actors;
    }

    public void setDirectors(List<TraktDirector> directors) {
        this.directors = directors;
    }

    public void setProducers(List<TraktProducer> producers) {
        this.producers = producers;
    }

    public void setWriters(List<TraktWriter> writers) {
        this.writers = writers;
    }

    public List<TraktActor> getActors() {
        return actors;
    }

    public List<TraktDirector> getDirectors() {
        return directors;
    }

    public List<TraktProducer> getProducers() {
        return producers;
    }

    public List<TraktWriter> getWriters() {
        return writers;
    }

    @Override
    public String toString() {
        return "TraktPeoples{" + "producers=" + producers + ", actors=" + actors + ", writers=" + writers + ", directors=" + directors + '}';
    }
    
    

}
