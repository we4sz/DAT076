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
package edu.chalmers.dat076.moviefinder.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Carl Jansson
 */
public class Constants {
    
    /**
     * Api key used to access media information from trakt.tv
     */
    public static final String TRAKT_API_KEY = "a93c5b3dee40604933b1b8069883a844";
    
    /**
     * The number of media to display in every list view.
     */
    public static final int MEDIA_DISPLAYED = 25;

    /**
     * A list of file endings indicating that there is no more useful information.
     */
    public static final List<String> MOVIE_FILE_ENDING_WORDS = Collections.unmodifiableList(new ArrayList<String>() {
        private static final long serialVersionUID = 1L;
        {
            add("1080p");
            add("720p");
            add("1920x1080");
            add("1280x720");
            add("mp4");
            add("avi");
            add("mkv");
            add("Bluray");
            add("BrRip");
            add("WEBRip");
            add("HDTV");
            add("Blu-ray");
            add("FLAC");
            add("AC3");
            add("h264");
            add("AAC");
            add("xvid");
            add("divx");
            add("x264");
            add("DTS");
            add("SCR");
            add("SWE");
            add("SWESUB");
            add("UNRATED");
            add("DC");
            add("THEATRICAL");
            add("EXTENDED");
        }
    });

}
