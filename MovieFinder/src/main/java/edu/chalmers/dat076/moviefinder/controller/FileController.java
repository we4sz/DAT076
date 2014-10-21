package edu.chalmers.dat076.moviefinder.controller;

import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for the api/files endpoint.
 *
 * @author John
 */
@Controller
@RequestMapping("api/files")
public class FileController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Page<Movie> listMovies() {
        return movieRepository.findAll(new PageRequest(0, 25));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Movie getMovieById(@PathVariable long id) {
        return movieRepository.findOne(id);
    }

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    public void getMovieStream(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        Movie m = movieRepository.findOne(id);
        try {
            ServletContext context = request.getServletContext();
            File downloadFile = new File(m.getFilePath());
            OutputStream outStream;
            // get MIME type of the file
            try (FileInputStream inputStream = new FileInputStream(downloadFile)) {
                // get MIME type of the file
                String mimeType = context.getMimeType(m.getFilePath());
                if (mimeType == null) {
                    mimeType = "video/mp4";
                }
                // set content attributes for the response
                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());
                // get output stream of the response
                outStream = response.getOutputStream();
                byte[] buffer = new byte[2048];
                int bytesRead = -1;
                // write bytes read from the input stream into the output stream
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
            outStream.close();
        } catch (IOException e) {

        }
    }
}
