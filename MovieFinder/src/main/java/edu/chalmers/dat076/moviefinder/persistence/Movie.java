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
package edu.chalmers.dat076.moviefinder.persistence;

import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * A movie entity. All fields defined in Media. This class is simply the
 * receiver of a TraktMovieResponse so it is saved correctly.
 *
 * @author Peter
 */
@Entity
public class Movie extends Media implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Movie() {
    }

    public Movie(String title, String filePath) {
        super(title, filePath);
        
    }
    
    public Movie(String filePath, TraktResponse data) {
        setFilePath( filePath);
        if (data != null) {
            if (data instanceof TraktMovieResponse) {
                setMovie(filePath, (TraktMovieResponse) data);
            } 
        }
    }

    
    public Movie(String filePath, TraktMovieResponse data) {
        setMovie(filePath, data);
    }
    
    private void setMovie(String filePath, TraktMovieResponse data){
        if (data != null) {
            setFilePath(filePath);
            setTitle(data.getTitle());
            setImdbRating(data.getRatings().getPercentage() / 10.0);
            setReleaseTime(data.getReleased());
            setPlot(data.getOverview());
            setImdbId(data.getImdbId());
            setRuntime(data.getRuntime());
            setActors(toActors(data.getPeople().getActors()));
            setGenres(data.getGenres());
            setPoster(getImage(data.getImages()));
            setRated(data.getCertification());
            setDirector(data.getPeople().getDirectors().size() > 0 ? data.getPeople().getDirectors().get(0).getName() : null);
        }
    }
    
}
