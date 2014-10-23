/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;
import java.nio.file.Path;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author John
 */
public interface ListeningPathDatabaseHandler {

    public void addPath(Path p) throws DataIntegrityViolationException;

    public void removePath(Path p) throws NullPointerException;
}
