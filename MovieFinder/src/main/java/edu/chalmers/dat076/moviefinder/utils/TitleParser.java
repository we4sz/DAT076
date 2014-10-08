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
            add("AC3");add("h264");add("AAC");add("xvid");add("divx");add("x264");
            //add("");add("");add("");add("");add("");add("");
        }
    };

    //Default constructor. Do we want this or strictly util class?
    public TitleParser() {
        sb = new StringBuilder();
    }

    /**
     * Parses a String and tries to derive a comprehensive movie or series title
     * from it.
     *
     * @param fileName The file name to be parsed
     * @return a hopefully better String
     */
    public String parseTitle(String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException();
        }

        sb.setLength(0);
        sb.append(fileName);

        if (sb.charAt(0) == '[') {
            removeUntil(sb, 0, ']');
        }

        removeFormating(sb);

        return sb.toString().trim();
    }

    /**
     * This method kind of controls everything...
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

    /**
     * Check if mySb has the potential to contain an episode of some series. It
     * is assumed that vital season information is available in index 0.
     *
     * @param mySb
     * @return true if mySb contains episode information
     */
    public boolean getEpisodePotential(StringBuilder mySb) {

        if (mySb.length() >= 4 && (mySb.charAt(0) == 'S' || mySb.charAt(0) == 's')) {

            if (Character.isDigit(mySb.charAt(1))) {
                if (Character.isDigit(mySb.charAt(3)) && (mySb.charAt(2) == 'E' || mySb.charAt(2) == 'e')) {
                    return true; // SxEx
                } else if (mySb.length() >= 6 && (Character.isDigit(mySb.charAt(2)) && Character.isDigit(mySb.charAt(4)) && (mySb.charAt(3) == 'E' || mySb.charAt(3) == 'e'))) {
                    return true; //SxxEx
                }
            } else if (mySb.length() > 13 && mySb.substring(0, 6).equalsIgnoreCase("season")) {
                for (int i = 6; i < mySb.length() - 7; i++) {
                    if (mySb.substring(i, i + 7).equalsIgnoreCase("episode")) {
                        return true; // season ... episode
                    }
                }
            }
        } else if (mySb.length() >= 3 && Character.isDigit(mySb.charAt(0))) {
            if ((Character.isDigit(mySb.charAt(2)) && (mySb.charAt(1) == 'x' || mySb.charAt(1) == 'X'))
                    || (mySb.length() >= 4 && Character.isDigit(mySb.charAt(1))
                    && Character.isDigit(mySb.charAt(3)) && (mySb.charAt(2) == 'x' || mySb.charAt(2) == 'X'))) {
                return true; // xXx xxXx
            }
        }
        return false;
    }

    /**
     * Before calling this method make sure mySb contains the right information
     * using getEpisodePotential. If data cant be found both season and episode
     * are set as negative one.
     *
     * @param mySb
     * @return
     */
    public Episode getEpisodeInfo(StringBuilder mySb) {
        int season = getNextNumber(mySb);
        int episode = getNextNumber(mySb);
        if (season < 0 || episode < 0) {
            season = -1;
            episode = -1;
            // Or throw some exception
        }
        return new Episode(season, episode);
    }

    /**
     * returns next number in mySb and deletes it and everything before it in
     * mySb.
     *
     * @param mySb
     * @return
     */
    public int getNextNumber(StringBuilder mySb) {
        int value = -1;
        boolean deleteAll = true;

        for (int i = 0; i < mySb.length(); i++) {

            // Character.isDigit(i) better or worse?
            if ('0' <= mySb.charAt(i) && mySb.charAt(i) <= '9') {
                if (value < 0) {
                    value = 0;
                }
                value = value * 10;
                value = value + Character.getNumericValue(mySb.charAt(i));
            } else if (value > 0 && !Character.isDigit(mySb.charAt(i))) {
                mySb.delete(0, i);
                deleteAll = false;
                break;
            }
        }
        if (deleteAll && value > 0) {
            mySb.setLength(0);
        }
        return value;
    }

    
    public class Episode{
        private final int season;
        private final int episode;
        public Episode(int s, int e){
            this.season=s;
            this.episode=e;
        }
        public int getSeason(){ return season; }
        public int getEpisode(){ return episode; }
    }
}
