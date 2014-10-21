/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import java.io.Serializable;

/**
 * A class for representing the JSON object expected from a login request.
 * @author John
 */
public class UserLoginModel implements Serializable{
    
    private String username;
    private String password;

    public UserLoginModel() {
    }

    public UserLoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
}
