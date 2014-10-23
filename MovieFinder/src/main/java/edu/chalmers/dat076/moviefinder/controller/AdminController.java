/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.controller;

import edu.chalmers.dat076.moviefinder.persistence.ListeningPath;
import edu.chalmers.dat076.moviefinder.persistence.ListeningPathRepository;
import edu.chalmers.dat076.moviefinder.service.FileThreadService;
import edu.chalmers.dat076.moviefinder.service.ListeningPathDatabaseHandler;
import edu.chalmers.dat076.moviefinder.service.MovieFileDatabaseHandler;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (To be) controller for the api/admin endpoint(s).
 *
 * This route is filtered via the UserFilter to only be accessible as a user
 * with role admin.
 *
 * @author John
 */
@Controller
@RequestMapping("api/admin")
public class AdminController {
    
    @Autowired
    private ListeningPathDatabaseHandler databaseHelper;
    
    @Autowired
    ListeningPathRepository listeningPathRepository;
    
    @Autowired
    private MovieFileDatabaseHandler moveiDatabaseHelper;
    
    @RequestMapping(value = "/addPath", method = RequestMethod.POST)
    public ResponseEntity<String> addPath(@RequestBody ListeningPath path) {
        File f = new File(path.getListeningPath());
        if (f.exists() && f.isDirectory()) {
            try {
                databaseHelper.addPath(f.toPath());
                FileThreadService t = FileThreadService.getInstance();
                t.addListningPath(f.toPath());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/removePath", method = RequestMethod.DELETE)
    public ResponseEntity<String> removePath(@RequestBody ListeningPath path) {
        File f = new File(path.getListeningPath());
        try {
            databaseHelper.removePath(f.toPath());
            FileThreadService t = FileThreadService.getInstance();
            t.removeListeningPath(f.toPath());
            moveiDatabaseHelper.removeFile(f.toPath());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/getPaths", method = RequestMethod.GET)
    public @ResponseBody
    Page<ListeningPath> getPaths() {
        return listeningPathRepository.findAll(new PageRequest(0, 25));
    }
    
}
