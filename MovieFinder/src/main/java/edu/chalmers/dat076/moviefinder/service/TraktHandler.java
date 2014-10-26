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
package edu.chalmers.dat076.moviefinder.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import edu.chalmers.dat076.moviefinder.model.HttpGetWithEquals;
import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.model.TraktMovieResponse;
import edu.chalmers.dat076.moviefinder.model.TraktResponse;
import edu.chalmers.dat076.moviefinder.model.TraktShowResponse;
import edu.chalmers.dat076.moviefinder.utils.Constants;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * A class for getting information about a movie from OMDB.
 *
 * @author Carl Jansson, Peter Eliasson
 */
@Service
public class TraktHandler {

    private HttpClient httpClient;

    public TraktHandler() {
        httpClient = HttpClientBuilder.create().build();
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

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
        String url = "http://api.trakt.tv/movie/summary.json/" + Constants.TRAKT_API_KEY + "/" + title.replace(" ", "-") + "-" + year;

        try {
            TraktMovieResponse movie = getGson().fromJson(readJsonFromUrl(url), TraktMovieResponse.class);
            if (movie == null || movie.getTitle() == null) {
                return null;
            }
            return movie;
        } catch (IOException | JsonSyntaxException ex) {
            return null;
        }
    }

    private Gson getGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public TraktShowResponse getByShowName(String title) {
        String url = "http://api.trakt.tv/show/summary.json/" + Constants.TRAKT_API_KEY + "/" + title.replace(" ", "-");
        try {
            TraktShowResponse showData = getGson().fromJson(readJsonFromUrl(url), TraktShowResponse.class);
            if (showData == null || showData.getTitle() == null) {
                return null;
            }
            return showData;
        } catch (IOException | JsonSyntaxException ex) {
            return null;
        }

    }

    public TraktEpisodeResponse getBySeasonEpisode(String title, int season, int episode) {
        String url = "http://api.trakt.tv/show/episode/summary.json/" + Constants.TRAKT_API_KEY + "/" + title.replace(" ", "-") + "/" + season + "/" + episode;
        try {
            TraktEpisodeResponse episodeData = getGson().fromJson(readJsonFromUrl(url), TraktEpisodeResponse.class);
            if (episodeData == null || episodeData.getEpisode() == null || episodeData.getEpisode().getTitle() == null) {
                return null;
            }
            return episodeData;
        } catch (IOException | JsonSyntaxException ex) {
            return null;
        }

    }

    private JsonObject readJsonFromUrl(String url) throws IOException, JsonSyntaxException {
        HttpGetWithEquals request = new HttpGetWithEquals(url);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Accept-Language", "sv-SE");
        request.addHeader("Accept", "application/json");
        HttpResponse response = getHttpClient().execute(request);
        return (JsonObject) new JsonParser().parse(EntityUtils.toString(response.getEntity(), "UTF-8"));
    }
}
