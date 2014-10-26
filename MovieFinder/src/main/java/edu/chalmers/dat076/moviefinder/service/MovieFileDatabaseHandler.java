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

import java.nio.file.Path;
import java.util.List;

/**
 * An interface for a class handling the processes of processing and saving a filename as some
 * entity in the database.
 */
public interface MovieFileDatabaseHandler {
    
    void setPaths(List<Path> paths);

    void updateFiles(List<Path> paths, Path basePath);
    
    /**
     * Takes a file path and attempts to store it in the database.
     * This operation can be unsuccessful for various reasons, if the file is decided to already exist
     * in the database, or if the file name can not be parsed.
     * @param path The absolute file path of the file. Including the file name.
     */
    void saveFile(Path path);

    /**
     * Takes a file path and a file name of a removed file and attempts to remove any references to this file
     * from the database.
     * @param path The absolute file path of the file. Including the file name.
     */
    void removeFile(Path path);
}
