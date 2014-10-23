package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.listener.FileSystemListener;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by Peter on 2014-10-23.
 */
public interface FileThreadService {
    void addListeningPath(Path p);

    void removeListeningPath(Path p);
}
