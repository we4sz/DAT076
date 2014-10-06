/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.utils;

import java.util.ArrayList;

/**
 * A class for parsing movie and series file names and derive a title.
 *
 * @author Carl Jansson
 */
public class TitleParser {

    private StringBuilder sb;

    private final ArrayList<String> finalWords = new ArrayList<String>() {
        {
            add("1080p");add("720p");add("1920x1080");add("1280x720");
            add("mp4");add("avi");add("mkv");
            add("Bluray");add("BrRip");add("WEBRip");add("HDTV");add("Blu-ray");add("FLAC");
            add("AC3");add("h264");add("AAC");add("xvid");add("x264");
            //add("");add("");add("");add("");add("");add("");add("");
        }
    };

    //Default constructor. Do we want this or strictly util class?
    public TitleParser() {
    }

    /**
     * Parses a String and tries to derive a comprehensive movie or series title
     * from it.
     *
     * @param fileName The file name to be parsed
     * @return a hopefully better String
     */
    public String parseTitle(String fileName) {
        if (sb == null) {
            sb = new StringBuilder(fileName);
        } else {
            sb.setLength(0);
            sb.append(fileName);
        }

        if (sb.charAt(0) == '[') {
            removeUntil(sb, 0, ']');
        }

        removeFormating(sb);

        return sb.toString().trim();
    }

    /**
     * This method rly does everything...
     *
     * @param mySb
     */
    public void removeFormating(StringBuilder mySb) {

        StringBuilder wordSb = new StringBuilder();

        boolean finalWord = true;

        for (int i = 0; i < mySb.length(); i++) {
            if (mySb.charAt(i) == '.' || mySb.charAt(i) == '-' || mySb.charAt(i) == '_' || mySb.charAt(i) == ' ') {

                if (finalWords.contains(wordSb.toString())) {
                    mySb.delete(i - (wordSb.length() + 1), mySb.length());
                    finalWord = false;
                    break;
                }
                mySb.replace(i, i + 1, " ");
                wordSb.setLength(0);

            } else if (mySb.charAt(i) == '[') {
                
                if (finalWords.contains(wordSb.toString())) {
                    mySb.delete(i - (wordSb.length() + 1), mySb.length());
                    finalWord = false;
                    break;
                }
                wordSb.setLength(0);
                
                //TODO Check if bracket contains something worth saving!!! 
                removeUntil(mySb, i, ']');
                i--; // Need to compensate for removing the bracket.
            } else if (mySb.charAt(i) == '(') {
                
                if (finalWords.contains(wordSb.toString())) {
                    mySb.delete(i - (wordSb.length() + 1), mySb.length());
                    finalWord = false;
                    break;
                }
                wordSb.setLength(0);
                
                //TODO Check if bracket contains something worth saving!!! 
                removeUntil(mySb, i, ')');
                i--; // Need to compensate for removing the bracket.
            } else {
                wordSb.append(mySb.charAt(i));
            }
        }
        if (finalWord && finalWords.contains(wordSb.toString())) {
            mySb.delete(mySb.length() - wordSb.length(), mySb.length());
        }
    }
    
    /**
     * Removes everything starting from index n until the char c.
     *
     * @param mySb
     * @param n
     * @param c
     */
    public void removeUntil(StringBuilder mySb, int n, char c) {
        for (int i = n; i <= mySb.length(); i++) {
            if (mySb.charAt(i) == c) {
                mySb.delete(n, i + 1);
                break;
            }
        }
    }
}
