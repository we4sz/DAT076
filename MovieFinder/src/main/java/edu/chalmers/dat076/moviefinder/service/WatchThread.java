/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author John
 */
public class WatchThread extends Thread {

    private WatchService watcher;
    private final File watchDir;
    private static final List<String> videoTypes = Arrays.asList(new String[]{"mkv", "mp4"});
    private WatchKey key;

    public WatchThread(File dir) {
        watchDir = dir;
    }

    @Override
    public void interrupt() {
        try {
            watcher.close();
            if (key != null) {
                key.cancel();
                key.notifyAll();
            }
        } catch (IOException ex) {
        }
        super.interrupt();
    }

    @Override
    public void run() {
        List<File> files = getAllPrograms(watchDir);
        Path myDir = Paths.get(watchDir.getAbsolutePath());
        try {
            watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE);
            key = watcher.take();
            while (key.isValid()) {
                try {
                    List<WatchEvent<?>> events = key.pollEvents();
                    if (events.size() > 0) {
                        for (WatchEvent event : events) {
                            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                                System.out.println("Created: " + event.context().toString());
                            }
                            if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                                System.out.println("Delete: " + event.context().toString());
                            }
                        }
                    } else {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(FileThreadService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException | InterruptedException ex) {
            java.util.logging.Logger.getLogger(FileThreadService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<File> getAllPrograms(File dir) {
        LinkedList l = new LinkedList<>();
        for (String fileName : dir.list()) {
            File t = new File(dir.getAbsolutePath() + "/" + fileName);
            if (t.isDirectory()) {
                l.addAll(getAllPrograms(t));
            } else {
                String ending = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                if ((videoTypes.contains(ending))) {
                    l.add(t);
                }
            }
        }
        return l;
    }

}
