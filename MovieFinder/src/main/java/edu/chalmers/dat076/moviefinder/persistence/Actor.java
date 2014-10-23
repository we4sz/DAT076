/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktActor;
import edu.chalmers.dat076.moviefinder.model.TraktImages;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author John
 */
@Entity
public class Actor extends AbstractEntity implements Serializable{

    private String name;
    private String actor_character;

    public Actor() {
    }

    public Actor(TraktActor a) {
        this.name = a.getName();
        this.actor_character = a.getCharacter();
    }

    public Actor(String name, String character, TraktImages images) {
        this.name = name;
        this.actor_character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return actor_character;
    }

    public void setCharacter(String character) {
        this.actor_character = character;
    }

}
