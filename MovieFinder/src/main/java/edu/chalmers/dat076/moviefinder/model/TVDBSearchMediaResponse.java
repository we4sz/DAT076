/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chalmers.dat076.moviefinder.model;

import java.io.Serializable;
import java.util.List;
import org.xmappr.Attribute;
import org.xmappr.Element;
import org.xmappr.RootElement;

/**
 *
 * @author John
 */
@RootElement(name = "Data")
public class TVDBSearchMediaResponse implements Serializable {

    @Element(targetType = TVDBEpisode.class)
    private TVDBEpisode Episode;

    @Element(targetType = TVDBSerie.class)
    private List<TVDBSerie> Series;

    public List<TVDBSerie> getSeries() {
        return Series;
    }

    public void setSeries(List<TVDBSerie> Series) {
        this.Series = Series;
    }

    public TVDBEpisode getEpisode() {
        return Episode;
    }

    public void setEpisode(TVDBEpisode Episode) {
        this.Episode = Episode;
    }
    
    
    

}
