/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.persistence.ListeningPath;
import edu.chalmers.dat076.moviefinder.persistence.ListeningPathRepository;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author John
 */
@Service
public class ListeningPathDatabaseHandlerImpl implements ListeningPathDatabaseHandler{

    @Autowired
    ListeningPathRepository repository;

    public ListeningPath addPath(Path p) throws DataIntegrityViolationException{
        return repository.save(new ListeningPath(p.toString()));
    }

    public void removePath(Path p) throws NullPointerException {
        ListeningPath l = repository.findByListeningPath(p.toString());
        if (l != null) {
            repository.delete(l);
        }else{
            throw new NullPointerException();
        }
    }

}
