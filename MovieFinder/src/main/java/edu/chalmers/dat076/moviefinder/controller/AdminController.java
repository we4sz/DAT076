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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private ListeningPathRepository listeningPathRepository;

    @Autowired
    private MovieFileDatabaseHandler movieDatabaseHelper;

    @Autowired
    private FileThreadService fileThreadService;

    @RequestMapping(value = "/addPath", method = RequestMethod.POST)
    public ResponseEntity<ListeningPath> addPath(@RequestBody ListeningPath path) {
        File f = new File(path.getListeningPath().toLowerCase());
        if (f.exists() && f.isDirectory()) {
            try {
                ListeningPath savedPath = databaseHelper.addPath(f.toPath());
                fileThreadService.addListeningPath(f.toPath());
                return new ResponseEntity<>(savedPath, HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/removePath/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removePath(@PathVariable long id) {
        ListeningPath lp = listeningPathRepository.findOne(id);
        if(lp != null) {
            File f = new File(lp.getListeningPath());
            try {
                databaseHelper.removePath(f.toPath());
                fileThreadService.removeListeningPath(f.toPath());
                movieDatabaseHelper.removeFile(f.toPath());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NullPointerException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getPaths", method = RequestMethod.GET)
    public @ResponseBody
    Page<ListeningPath> getPaths() {
        return listeningPathRepository.findAll(new PageRequest(0, 25));
    }

}
