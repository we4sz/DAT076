/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.listener.FileSystemListener;
import edu.chalmers.dat076.moviefinder.model.OmdbMediaResponse;
import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author John
 */
@Service
public class FileThreadService implements FileSystemListener {

    private final static Logger LOGGER = Logger.getLogger(FileThreadService.class.getName());

    @Autowired
    private MovieFileDatabaseHelper databaseHelper;

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
    public void initFile(String path, String name) {
        LOGGER.info("initFile: " + path + " " + name);
        databaseHelper.saveFile(path, name);
    }

    @Override
    public void newFile(String path, String name) {
        LOGGER.info("newFile: " + path + " " + name);

        databaseHelper.saveFile(path, name);
    }

    @Override
    public void oldPath(String path, String name) {
        LOGGER.info("oldPath: " + path + " " + name);

        databaseHelper.removeFile(path, name);
    }

}
