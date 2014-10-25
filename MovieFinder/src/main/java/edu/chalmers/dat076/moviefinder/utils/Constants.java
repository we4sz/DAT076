/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     * A list of file endings indicating that there is no more useful information.
     */
    public static final List<String> MOVIE_FILE_ENDING_WORDS = Collections.unmodifiableList(new ArrayList<String>() {
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
