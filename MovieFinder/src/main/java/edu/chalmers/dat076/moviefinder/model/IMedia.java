/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.model;

import java.nio.file.Path;

/**
 * An interface for all media types.
 *
 * @author Carl Jansson
 */
public interface IMedia {
    
    public Path getPath();
    
    public void setPath(Path path);
    
    public String getFileName();
    
    public void setFileName(String name);
    
    public String getImdbId();
    
    public void setImdbId(String imdbID);
    
}
