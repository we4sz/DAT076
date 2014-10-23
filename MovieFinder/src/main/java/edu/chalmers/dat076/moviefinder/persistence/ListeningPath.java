/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author John
 */
@Entity
public class ListeningPath extends AbstractEntity implements Serializable {

    @Column(nullable = false, unique = true)
    private String listeningPath;

    public ListeningPath() {
    }

    public ListeningPath(String listeningPath) {
        this.listeningPath = listeningPath;
    }

    public String getListeningPath() {
        return listeningPath;
    }

    public void setListeningPath(String listeningPath) {
        this.listeningPath = listeningPath;
    }

}
