/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.listener;

import java.util.List;


/**
 *
 * @author John
 */
public interface FileSystemListener {
    
    public void initFile(String paths);
    
    public void newFile(String path);
    
    public void oldPath(String path);
    
}
