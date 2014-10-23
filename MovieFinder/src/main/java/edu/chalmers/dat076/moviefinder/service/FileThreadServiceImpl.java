/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.listener.FileSystemListener;
import edu.chalmers.dat076.moviefinder.persistence.ListeningPath;
import edu.chalmers.dat076.moviefinder.persistence.ListeningPathRepository;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A service for monitoring the file system for changes.
 *
 * @author John
 */
@Service
public class FileThreadServiceImpl implements FileThreadService, FileSystemListener {

    private final static Logger LOGGER = Logger.getLogger(FileThreadServiceImpl.class.getName());

    @Autowired
    private MovieFileDatabaseHandler movieDatabaseHelper;

    @Autowired
    private ListeningPathRepository listeningPathRepository;

    @Autowired
    private ListeningPathDatabaseHandler databaseHelper;

    private LinkedList<WatchThread> threads;

    @PostConstruct
    @Transactional
    public void init() {
        threads = new LinkedList<>();
        Iterable<ListeningPath> paths = listeningPathRepository.findAll();
        List<Path> checkPaths = new LinkedList<>();
        for (ListeningPath p : paths) {
            File f = new File(p.getListeningPath());
            if (f.exists() && f.isDirectory()) {
                addListeningPath(f.toPath());
                checkPaths.add(f.toPath());
            } else {
                databaseHelper.removePath(f.toPath());
                movieDatabaseHelper.removeFile(f.toPath());
            }
        }
        
        movieDatabaseHelper.setPaths(checkPaths);
    }


    @Override
    public void addListeningPath(Path p) {
        try {
            threads.add(new WatchThread(p.toFile()));
            threads.getLast().setListener(this);
            threads.getLast().start();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeListeningPath(Path p) {
        for (int i = 0; i < threads.size(); i++) {
            if (threads.get(i).getWatchPath().equals(p)) {
                movieDatabaseHelper.removeFile(p);
                threads.get(i).interrupt();
                try {
                    threads.get(i).join();
                } catch (InterruptedException ex) {
                }
                threads.remove(i);
                break;
            }
        }
    }

    @PreDestroy
    public void destroy() {
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
            movieDatabaseHelper.updateFiles(paths, basePath);
        } catch (RuntimeException e) {
            LOGGER.info(e.getMessage());
        }

    }

    @Override
    public void newFile(Path path) {
        LOGGER.info("newFile: " + path);
        try {
            movieDatabaseHelper.saveFile(path);
        } catch (RuntimeException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void oldPath(Path path) {
        LOGGER.info("oldPath: " + path);
        movieDatabaseHelper.removeFile(path);
    }

}
