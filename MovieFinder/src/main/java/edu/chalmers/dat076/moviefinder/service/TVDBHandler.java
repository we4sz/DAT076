/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.service;

import edu.chalmers.dat076.moviefinder.model.TVDBData;
import edu.chalmers.dat076.moviefinder.model.TVDBEpisode;
import edu.chalmers.dat076.moviefinder.model.TVDBSearchMediaResponse;
import edu.chalmers.dat076.moviefinder.model.TVDBSerie;
import edu.chalmers.dat076.moviefinder.model.TemporaryMedia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
import org.xmappr.Xmappr;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.taglibs.standard.tag.common.core.Util;

/**
 *
 * @author John
 */
@Service
public class TVDBHandler {

    /**
     *
     * @param tm
     * @return the most recent movie with the best title match
     * @throws java.io.IOException
     */
    public TVDBData getEpisodeAndSerie(TemporaryMedia tm) throws NullPointerException, IOException {
        String searchUrl = "http://thetvdb.com/api/GetSeries.php?seriesname=" + Util.URLEncode(tm.getName(), "UTF-8");

        HttpClient c = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(searchUrl);
        HttpResponse response = c.execute(get);
        Xmappr xm = new Xmappr(TVDBSearchMediaResponse.class);
        TVDBSearchMediaResponse sc = (TVDBSearchMediaResponse) xm.fromXML(new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8")));

        if(sc == null || sc.getSeries() == null || sc.getSeries().isEmpty()){
            return null;
        }
        
        System.out.println("(found done");
        return new TVDBData(getSerie(sc.getSeries().get(0).getId()),getEpisode(sc.getSeries().get(0).getId(), tm.getSeason(), tm.getEpisode()));
    }

    private TVDBEpisode getEpisode(String id, int season, int episode) throws IOException {
        String episodeURL = "http://thetvdb.com/api/E1FE1E39F873EBDD/series/" + id + "/default/" + season + "/" + episode + "/en.xml";
        HttpClient c = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(episodeURL);
        HttpResponse response = c.execute(get);

        Xmappr xm = new Xmappr(TVDBSearchMediaResponse.class);
        TVDBSearchMediaResponse sc = (TVDBSearchMediaResponse) xm.fromXML(new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8")));

        if (sc == null || sc.getEpisode() == null) {
            return null;
        }
        System.out.println("(episode done");
        return sc.getEpisode();
    }

    private TVDBSerie getSerie(String id) throws IOException {
        String serieURL = "http://thetvdb.com/api/8E78D675E1E52FDE/series/" + id + "/en.xml";
        HttpClient c = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(serieURL);
        HttpResponse response = c.execute(get);

        Xmappr xm = new Xmappr(TVDBSearchMediaResponse.class);
        TVDBSearchMediaResponse sc = (TVDBSearchMediaResponse) xm.fromXML(new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8")));

        if (sc == null || sc.getSeries().isEmpty()) {
            return null;
        }

        System.out.println("(serie done");
        return sc.getSeries().get(0);
    }

}