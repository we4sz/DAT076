/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Calendar;


/**
 * A class for parsing movie and series file names and deriving useful
 * information.
 *
 * @author Carl Jansson
 */
@Service
public class TitleParser {

    private StringBuilder sb;

    
    //Default constructor. Do we want this or strictly util class?
    public TitleParser() {
        sb = new StringBuilder();
    }
    
    /**
     * Parses a String and tries to derive comprehensive movie or series
     * information from it.
     *
     * @param fileName The file name to be parsed
     * @return TemporaryMedia with hopefully useful information
     */
    public TemporaryMedia parseMedia(String fileName){
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (sb == null) {
            sb = new StringBuilder();
        }
        
        sb.setLength(0);
        sb.append(fileName);

        return getInformation(sb);
    }
    
    /**
     * This method kind of controls everything...
     *
     * @param mySb
     * @return TemporaryMedia with everything
     */
    private TemporaryMedia getInformation(StringBuilder mySb) {

        TemporaryMedia returnMedia= new TemporaryMedia();
        StringBuilder wordSb = new StringBuilder();

        int tmpYear;
        int year = -1;
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        
        boolean deleteYear = false;
        boolean finalWord = true;

        for (int i = 0; i < mySb.length(); i++) {
            if (mySb.charAt(i) == '.' || mySb.charAt(i) == ' ' || mySb.charAt(i) == '-' || mySb.charAt(i) == '_') {

                if (Constants.MOVIE_FILE_ENDING_WORDS.contains(wordSb.toString())) {
                    mySb.delete(i - (wordSb.length() + 1), mySb.length());
                    finalWord = false;
                    break;
                }
                mySb.replace(i, i + 1, " ");
                
                tmpYear = checkForYear(wordSb);
                if (tmpYear > 1900 && tmpYear < thisYear) {
                    year = tmpYear;
                    deleteYear = true;
                }
                wordSb.setLength(0);

            } else if ( wordSb.length()==0 && (mySb.charAt(i) == 'S' || mySb.charAt(i) == 's' || Character.isDigit(mySb.charAt(i)))) {

                StringBuilder whatsLeft = new StringBuilder(mySb.subSequence(i, mySb.length()));
                if (getEpisodePotential(whatsLeft)) {
                    TemporaryMedia tmpMedia = getEpisodeInfo(whatsLeft);
                    returnMedia.setIsMovie(false);
                    returnMedia.setSeason(tmpMedia.getSeason());
                    returnMedia.setEpisode(tmpMedia.getEpisode());
                    mySb.delete(i, mySb.length() - whatsLeft.length());
                    //if (i > 0){ if it becomes -1 it will become 0 next loop
                    i--; // Need to compensate for deleting.
                    
                } else {
                    wordSb.append(mySb.charAt(i));
                }
            } else if (mySb.charAt(i) == '[' || mySb.charAt(i) == '(') {

                if (Constants.MOVIE_FILE_ENDING_WORDS.contains(wordSb.toString())) {
                    mySb.delete(i - (wordSb.length() + 1), mySb.length());
                    finalWord = false;
                    break;
                }
                tmpYear = checkForYear(wordSb);
                if (tmpYear > 1900 && tmpYear < thisYear) {
                    year = tmpYear;
                    deleteYear = true;
                }
                wordSb.setLength(0);

                if (mySb.charAt(i) == '[') {
                    tmpYear = removeUntil(mySb, i, ']');
                } else if (mySb.charAt(i) == '(') {
                    tmpYear = removeUntil(mySb, i, ')');
                }
                if (tmpYear > 1900 && tmpYear < thisYear) {
                    year = tmpYear;
                    deleteYear = false;
                }
                i--; // Need to compensate for removing bracket.
                
            } else {
                wordSb.append(mySb.charAt(i));
            }
        }
        
        if (finalWord && Constants.MOVIE_FILE_ENDING_WORDS.contains(wordSb.toString())) {
            mySb.delete(mySb.length() - wordSb.length(), mySb.length());
        } else {
            tmpYear = checkForYear(wordSb);
            if (tmpYear > 1900 && tmpYear < thisYear) {
                year = tmpYear;
                deleteYear = true;
            }
        }
        if (deleteYear) {
            int i = mySb.lastIndexOf( year + "" );
            mySb.delete( i, i+4 ) ;
        }
        
        returnMedia.setYear(year);
        returnMedia.setName(mySb.toString().trim());
        
        return returnMedia;
    }
    
    
    public int checkForYear(CharSequence cs){
        int year = 0;
        if (cs.length() == 4){
            
            for (int i = 0; i < 4; i++) {
                if (Character.isDigit(cs.charAt(i))) {
                    year = year * 10;
                    year = year + Character.getNumericValue(cs.charAt(i));
                } else {
                    return -1;
                }
            }
        }
        return year;
    }

    /**
     * Removes everything starting from index n until the char c. If interval
     * contains a positive 4 digit number (and nothing else) it is returned,
     * otherwise -1 is returned.
     *
     * @param mySb
     * @param n
     * @param c
     * @return if interval contains a number it is returned
     */
    public int removeUntil(StringBuilder mySb, int n, char c) {
        int re = -1;

        for (int i = n; i <= mySb.length(); i++) {
            if (mySb.charAt(i) == c) {
                if (i - n == 5) {
                    re = checkForYear(mySb.subSequence(n + 1, i));
                }
                mySb.delete(n, i + 1);
                break;
            }
        }
        return re;
    }

    /**
     * Check if mySb has the potential to contain an episode of some series. It
     * is assumed that vital season information is available in index 0.
     *
     * @param mySb
     * @return true if mySb contains season and episode information
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
     * are set as negative one. It is assumed the first available number is
     * season number and second number is episode.
     *
     * @param mySb
     * @return TemporaryMedia containing season and episode
     */
    public TemporaryMedia getEpisodeInfo(StringBuilder mySb) {
        int season = getNextNumber(mySb);
        int episode = getNextNumber(mySb);
        if (season < 0 || episode < 0) {
            season = -1;
            episode = -1;
            // Or throw some exception
        }
        TemporaryMedia tmp = new TemporaryMedia();
        tmp.setSeason(season);
        tmp.setEpisode(episode);
        return tmp;
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
                } else {
                    value = value * 10;
                }
                value = value + Character.getNumericValue(mySb.charAt(i));
            } else if ( value > 0 ) {
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
}
