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
package edu.chalmers.dat076.moviefinder.model;

import java.util.List;

/**
 *
 * @author John
 */
public class TraktShowResponse implements TraktResponse {

    private String title;
    private int year;
    private String url;
    private long firstAired;
    private String firstAiredIso;
    private long firstAiredUtc;
    private String country;
    private String overview;
    private int runtime;
    private String status;
    private String network;
    private String airDay;
    private String airDayUtc;
    private String airTime;
    private String airTimeUtc;
    private String certification;
    private String imdbId;
    private int tvdbId;
    private int tvrageId;
    private long lastUpdated;
    private String poster;
    private TraktImages images;
    private TraktRatings ratings;
    private TraktPeoples people;
    private List<String> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public TraktImages getImages() {
        return images;
    }

    public void setImages(TraktImages images) {
        this.images = images;
    }

    public TraktRatings getRatings() {
        return ratings;
    }

    public void setRatings(TraktRatings ratings) {
        this.ratings = ratings;
    }

    public TraktPeoples getPeople() {
        return people;
    }

    public void setPeople(TraktPeoples people) {
        this.people = people;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public long getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(long firstAired) {
        this.firstAired = firstAired;
    }

    public String getFirstAiredIso() {
        return firstAiredIso;
    }

    public void setFirstAiredIso(String firstAiredIso) {
        this.firstAiredIso = firstAiredIso;
    }

    public long getFirstAiredUtc() {
        return firstAiredUtc;
    }

    public void setFirstAiredUtc(long firstAiredUtc) {
        this.firstAiredUtc = firstAiredUtc;
    }

    public String getAirDay() {
        return airDay;
    }

    public void setAirDay(String airDay) {
        this.airDay = airDay;
    }

    public String getAirDayUtc() {
        return airDayUtc;
    }

    public void setAirDayUtc(String airDayUtc) {
        this.airDayUtc = airDayUtc;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getAirTimeUtc() {
        return airTimeUtc;
    }

    public void setAirTimeUtc(String airTimeUtc) {
        this.airTimeUtc = airTimeUtc;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public int getTvdbId() {
        return tvdbId;
    }

    public void setTvdbId(int tvdbId) {
        this.tvdbId = tvdbId;
    }

    public int getTvrageId() {
        return tvrageId;
    }

    public void setTvrageId(int tvrageId) {
        this.tvrageId = tvrageId;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "TraktShowResponse{" + "title=" + title + ", year=" + year + ", url=" + url + ", firstAired=" + firstAired + ", firstAiredIso=" + firstAiredIso + ", firstAiredUtc=" + firstAiredUtc + ", country=" + country + ", overview=" + overview + ", runtime=" + runtime + ", status=" + status + ", network=" + network + ", airDay=" + airDay + ", airDayUtc=" + airDayUtc + ", airTime=" + airTime + ", airTimeUtc=" + airTimeUtc + ", certification=" + certification + ", imdbId=" + imdbId + ", tvdbId=" + tvdbId + ", tvrageId=" + tvrageId + ", lastUpdated=" + lastUpdated + ", poster=" + poster + ", images=" + images + ", ratings=" + ratings + ", people=" + people + ", genres=" + genres + '}';
    }

}
