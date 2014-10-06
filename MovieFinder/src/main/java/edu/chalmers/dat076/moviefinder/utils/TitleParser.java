/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.utils;

/**
 * A class for parsing movie and series file names and derive a title.
 *
 * @author Carl Jansson
 */
public class TitleParser {

    private StringBuilder sb;

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
            removeBrackets(sb, 0);
        }

        return sb.toString();
    }

    
    public void removeFormating(StringBuilder mySb) {
        replaceChars(mySb,'.');
        replaceChars(mySb,'-');
        replaceChars(mySb,'_');
    }
    
    /**
     * in mySb replace every instance of char c with a space.
     * @param mySb
     * @param c 
     */
    private void replaceChars(StringBuilder mySb, char c ){
        
        for (int i = 0; i < mySb.length(); i++){
            if (mySb.charAt(i) == c ) {
                mySb.deleteCharAt(i);
                mySb.replace(i, i, " ");
            }
        }
    }
    
    /**
     * Delete everything in mySb from index n
     * @param mySb
     * @param n 
     */
    public void deleteRest(StringBuilder mySb, int n){
        mySb.delete(n, mySb.length());
    }

    /**
     * Removes brackets and their content from StringBuilder. Everything
     * starting from index n until the char ']' will be removed.
     *
     * @param mySb StringBuilder containing the String
     * @param n index to start looking from. First index is 0.
     */
    public void removeBrackets(StringBuilder mySb, int n) {
        for (int i = n; i <= mySb.length(); i++) {
            if (mySb.charAt(i) == ']') {
                mySb.delete(n, i + 1);
                break;
            }
        }
    }

}
