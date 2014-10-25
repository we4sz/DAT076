package edu.chalmers.dat076.moviefinder.service;

import java.nio.file.Path;

/**
 * Created by Peter on 2014-10-23.
 */
public interface FileThreadService {
    void addListeningPath(Path p);

    void removeListeningPath(Path p);
}
