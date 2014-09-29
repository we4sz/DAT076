/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author John
 */
public class FileThreadService{

    private static final Logger logger = LoggerFactory.getLogger(FileThreadService.class);
    private List<File> checkFolders;
    private LinkedList<WatchThread> threads;
    
    public void init() throws IOException {
        checkFolders = new LinkedList<>();
        checkFolders.add(new File("C:/film"));
        threads = new LinkedList<>();
        for(File f : checkFolders){  
            threads.add(new WatchThread(f));
            threads.getLast().start();
        }
    }
    
    public void destory(){
        for(WatchThread t : threads){
            t.interrupt();
            try {
                t.join();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(FileThreadService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
