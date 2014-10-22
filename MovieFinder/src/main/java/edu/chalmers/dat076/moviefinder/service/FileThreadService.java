/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.listener.FileSystemListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

/**
 * A service for monitoring the file system for changes.
 *
 * @author John
 */
@Service
public class FileThreadService implements FileSystemListener {

    private final static Logger LOGGER = Logger.getLogger(FileThreadService.class.getName());

    @Autowired
    private MovieFileDatabaseHandler databaseHelper;

    private List<File> checkFolders;
    private LinkedList<WatchThread> threads;

    @PostConstruct
    public void init() {
        checkFolders = new LinkedList<>();
        checkFolders.add(new File("C:/film"));
        threads = new LinkedList<>();
        for (File f : checkFolders) {
            try {
                threads.add(new WatchThread(f));
                threads.getLast().setListener(this);
                threads.getLast().start();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    @PreDestroy
    public void destory() {
        for (WatchThread t : threads) {
            t.interrupt();
            try {
                t.join();
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public void initFiles(List<Path> paths, Path basePath) {
        LOGGER.info("initFiles: " + paths.size());
        try {
            databaseHelper.updateFiles(paths, basePath);
        } catch (RuntimeException e) {
            LOGGER.info(e.getMessage());
        }

    }

    @Override
    public void newFile(Path path) {
        LOGGER.info("newFile: " + path);
        try {
            databaseHelper.saveFile(path);
        } catch (RuntimeException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void oldPath(Path path) {
        LOGGER.info("oldPath: " + path);
        databaseHelper.removeFile(path);
    }

}
