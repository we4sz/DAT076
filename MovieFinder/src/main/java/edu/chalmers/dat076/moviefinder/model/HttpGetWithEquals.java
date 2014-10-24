/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import org.apache.http.client.methods.HttpGet;

/**
 *
 * @author John
 */
public class HttpGetWithEquals extends HttpGet {

    public HttpGetWithEquals(String uri) {
        super(uri);
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HttpGetWithEquals) {
            HttpGetWithEquals h = (HttpGetWithEquals) obj;
            return h.getURI().toString().equals(getURI().toString());
        }
        return false;
    }

}
