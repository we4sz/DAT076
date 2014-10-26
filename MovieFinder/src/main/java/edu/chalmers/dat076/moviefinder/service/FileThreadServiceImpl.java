/* 
 * The MIT License
 *
 * Copyright 2014 Anton, Carl, John, Peter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.listener.FileSystemListener;
import edu.chalmers.dat076.moviefinder.persistence.ListeningPath;
import edu.chalmers.dat076.moviefinder.persistence.ListeningPathRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
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
