/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chalmers.dat076.moviefinder.model;

import java.nio.file.Path;

/**
 * An abstract class for all media.
 *
 * @author Carl Jansson
 */
public abstract class AbstractMedia implements IMedia {

    private Path filePath;
    private String fileName;
    private String imdbID;
    
    
    @Override
    public Path getPath() {
        return filePath;
    }

    @Override
    public void setPath(Path path) {
        this.filePath=path;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String name) {
        this.fileName=name;
    }

    @Override
    public String getImdbId() {
        return imdbID;
    }

    @Override
    public void setImdbId(String imdbID) {
        this.imdbID=imdbID;
    }
    
}
