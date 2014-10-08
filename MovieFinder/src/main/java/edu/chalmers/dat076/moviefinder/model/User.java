/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import java.io.Serializable;

/**
 *
 * @author John
 */
public class User implements Serializable {
    
    private String username;
    private UserRole role;

    public User(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
    
    
}
