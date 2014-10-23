/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import edu.chalmers.dat076.moviefinder.model.TraktShowReponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

/**
 * A class for getting information about a movie from OMDB.
 *
 * @author Carl Jansson, Peter Eliasson
 */
@Service
public class TraktHandler {

    /**
     * Looks up omdb data by Title and if possible year. Best used with movies
     *
     * @param tmp TemporaryMedia with a title and maybe a year
     * @return data found by omdb or null
     */
    public TraktResponse getByTmpMedia(TemporaryMedia tmp) {
        if (tmp.IsMovie()) {
            if (tmp.getName() != null && tmp.getYear() > 1900) {
                return getByTitleYear(tmp.getName(), tmp.getYear());
            }
        } else {
            return getBySeasonEpisode(tmp.getName(), tmp.getSeason(), tmp.getEpisode());
        }
        return null;
    }

    /**
     * Looks up a movie by its title and year.
     *
     * @param title The title of the movie.
     * @param year The year the movie was released.
     * @return the biggest thing from the year with the best title match, or
     * null if no movie could be found.
     */
    public TraktMovieResponse getByTitleYear(String title, int year) throws NullPointerException {
        String url = "http://api.trakt.tv/movie/summary.json/a93c5b3dee40604933b1b8069883a844/" + title.replace(" ", "-") + "-" + year;

        try {
            TraktMovieResponse movie = new Gson().fromJson(readJsonFromUrl(url), TraktMovieResponse.class);
            if (movie.getTitle() == null) {
                return null;
            }
            return movie;
        } catch (IOException ex) {
            return null;
        }
    }

    public TraktShowReponse getByShowName(String title) {
        String url = "http://api.trakt.tv/show/summary.json/a93c5b3dee40604933b1b8069883a844/" + title.replace(" ", "-");
        try {
            TraktShowReponse showData = new Gson().fromJson(readJsonFromUrl(url), TraktShowReponse.class);
            if (showData.getTitle() == null) {
                return null;
            }
            return showData;
        } catch (IOException ex) {
            return null;
        }

    }

    public TraktEpisodeResponse getBySeasonEpisode(String title, int season, int episode) {
        String url = "http://api.trakt.tv/show/episode/summary.json/a93c5b3dee40604933b1b8069883a844/" + title.replace(" ", "-") + "/" + season + "/" + episode;
        try {
            TraktEpisodeResponse episodeData = new Gson().fromJson(readJsonFromUrl(url), TraktEpisodeResponse.class);
            if (episodeData.getEpisode().getTitle() == null) {
                return null;
            }
            return episodeData;
        } catch (IOException ex) {
            return null;
        }

    }

    private static JsonObject readJsonFromUrl(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Accept-Language", "sv-SE");
        request.addHeader("Accept", "application/json");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        return (JsonObject) new JsonParser().parse(rd);
    }
}
