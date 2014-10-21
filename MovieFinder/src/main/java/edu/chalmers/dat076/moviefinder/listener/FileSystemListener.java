/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.listener;

import java.util.List;


/**
 * A listener for file system change events.
 * @author John
 */
public interface FileSystemListener {

    /**
     * initFile is called the first time a file is seen.
     * @param path The absolute path to the file. Includes the name of the file.
     * @param name The name of the file.
     */
    public void initFile(String path, String name);

    /**
     * newFile is called when a new file is added to a directory that is being watched.
     * @param path The absolute path to the file. Includes the name of the file.
     * @param name The name of the file.
     */
    public void newFile(String path, String name);

    /**
     * oldPath is called when a file is removed/renamed in a directory that is being watched.
     * @param path The absolute path to the file. Includes the name of the file.
     * @param name The name of the file.
     */
    public void oldPath(String path, String name);
    
}
