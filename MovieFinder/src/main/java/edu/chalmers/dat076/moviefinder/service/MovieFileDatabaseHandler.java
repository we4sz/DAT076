package edu.chalmers.dat076.moviefinder.service;

/**
 * An interface for a class handling the processes of processing and saving a filename as some
 * entity in the database.
 */
public interface MovieFileDatabaseHandler {
    /**
     * Takes a file path and a file name and attempts to store it in the database.
     * This operation can be unsuccessful for various reasons, if the file is decided to already exist
     * in the database, or if the file name can not be parsed.
     * @param path The absolute file path of the file. Including the file name.
     * @param name The file name.
     * @return True if the file was stored, false if not.
     */
    boolean saveFile(String path, String name);

    /**
     * Takes a file path and a file name of a removed file and attempts to remove any references to this file
     * from the database.
     * @param path The absolute file path of the file. Including the file name.
     * @param name The file name.
     * @return True if the file was removed, else false.
     */
    boolean removeFile(String path, String name);
}
