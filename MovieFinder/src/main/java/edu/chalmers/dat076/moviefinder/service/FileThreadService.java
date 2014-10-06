/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.listener.FileSystemListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author John
 */
public class FileThreadService implements FileSystemListener{

    private List<File> checkFolders;
    private LinkedList<WatchThread> threads;
    
    public void init() throws IOException {
        checkFolders = new LinkedList<>();
        checkFolders.add(new File("C:/film"));
        threads = new LinkedList<>();
        for(File f : checkFolders){  
            threads.add(new WatchThread(f));
            threads.getLast().setListener(this);
            threads.getLast().start();
        }
    }
    
    public void destory(){
        for(WatchThread t : threads){
            t.interrupt();
            try {
                t.join();
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void initFiles(List<String> paths) {
        System.out.println(paths.size());
    }

    @Override
    public void newFile(String path) {
        System.out.println("created "+path);
    }

    @Override
    public void oldPath(String path) {
        System.out.println("delted "+path);
    }

}
