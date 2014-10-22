/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.listener.FileSystemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import static java.nio.file.LinkOption.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Thread for listening to file system changes, such as adding and removing of
 * files, in a specific directory.
 *
 * @author John
 */
public class WatchThread extends Thread {

    private WatchService watcher;
    private final Path watchPath;
    private static final List<String> videoTypes = Arrays.asList(new String[]{"avi", "mkv", "mp4","mov"});
    private Map<WatchKey, Path> keys;
    private FileSystemListener listener;
    private final static Logger LOGGER = Logger.getLogger(FileThreadService.class.getName());

    public WatchThread(File dir) throws IOException {
        watchPath = Paths.get(dir.getAbsolutePath());
        keys = new HashMap<>();
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        Path prev = keys.get(key);
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start, boolean init) throws IOException {
        // register directory and sub-directories
        final List<Path> paths = new LinkedList<>();
        
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String ending = file.toString().substring(file.toString().lastIndexOf(".") + 1, file.toString().length());
                if (videoTypes.contains(ending.toLowerCase())) {
                    paths.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                register(dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }

        });
        if(init){
            listener.initFiles(paths, watchPath);
        }else{
            for(Path p : paths){
                listener.newFile(p);
            }
        }
    }

    public void setListener(FileSystemListener listener) {
        this.listener = listener;
    }

    @Override
    public void interrupt() {
        try {
            for (WatchKey k : keys.keySet()) {
                k.cancel();
            }
            watcher.close();
            keys = new HashMap<>();
        } catch (IOException ex) {
        }
        super.interrupt();
    }

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    @Override
    public void run() {

        try {
            watcher = FileSystems.getDefault().newWatchService();
            registerAll(watchPath,true);
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            System.out.println("mamma");
            System.out.println(ex.getMessage());
        }

        while (!keys.isEmpty()) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException | ClosedWatchServiceException x) {
                return;
            }
            Path dir = keys.get(key);
            if (dir == null) {
                // System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child,false);
                        } else {
                            String ending = child.toString();
                            ending = ending.substring(ending.lastIndexOf(".") + 1, ending.length());

                            if (videoTypes.contains(ending.toLowerCase())) {
                                listener.newFile(child);
                            }
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                } else if (kind == ENTRY_DELETE) {
                    listener.oldPath(child);
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }

        }
    }

}
