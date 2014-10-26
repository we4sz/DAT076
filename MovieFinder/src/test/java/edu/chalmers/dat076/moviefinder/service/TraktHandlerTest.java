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

import edu.chalmers.dat076.moviefinder.model.HttpGetWithEquals;
import edu.chalmers.dat076.moviefinder.model.TraktEpisodeResponse;
import edu.chalmers.dat076.moviefinder.utils.Constants;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicStatusLine;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
/**
 *
 * @author Carl Jansson
 */
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TraktHandlerTest {
    
    @Configuration
    static class ContextConfiguration {
        @Bean
        public TraktHandler traktHandler() {
            return new TraktHandler();
        }

    }
    @Autowired
    private TraktHandler instance;

    private String episodeTGW = "{\"show\":{\"title\":\"The Good Wife\",\"year\":2009,\"url\":\"http://trakt.tv/show/the-good-wife\",\"first_aired\":1253678400,\"first_aired_iso\":\"2009-09-22T21:00:00-04:00\",\"first_aired_utc\":1253667600,\"country\":\"United States\",\"overview\":\"The Good Wife is a drama starring Emmy Award winner Julianna Margulies as a wife and mother who must assume full responsibility for her family and re-enter the workforce after her husband's very public sex and political corruption scandal lands him in jail.\",\"runtime\":60,\"network\":\"CBS\",\"air_day\":\"Sunday\",\"air_time\":\"9:00pm\",\"certification\":\"TV-14\",\"imdb_id\":\"tt1442462\",\"tvdb_id\":95451,\"tvrage_id\":22755,\"images\":{\"poster\":\"http://slurm.trakt.us/images/posters/338.10.jpg\",\"fanart\":\"http://slurm.trakt.us/images/fanart/338.10.jpg\",\"banner\":\"http://slurm.trakt.us/images/banners/338.10.jpg\"},\"ratings\":{\"percentage\":87,\"votes\":2454,\"loved\":2341,\"hated\":113},\"genres\":[\"Drama\",\"Crime\",\"Mystery\"]},\"episode\":{\"season\":6,\"number\":3,\"tvdb_id\":4999282,\"imdb_id\":\"\",\"title\":\"Dear God\",\"overview\":\"Joy Grubick, a pretrial service officer, interviews Cary's colleagues to determine if he should remain behind bars; a client's case ends up in Christian arbitration.\",\"url\":\"http://trakt.tv/show/the-good-wife/season/6/episode/3\",\"first_aired\":1412568000,\"first_aired_iso\":\"2014-10-05T21:00:00-04:00\",\"first_aired_utc\":1412557200,\"images\":{\"screen\":\"http://slurm.trakt.us/images/episodes/338-6-3.10.jpg\"},\"ratings\":{\"percentage\":83,\"votes\":266,\"loved\":264,\"hated\":2}}}";
    private String episodeHIMYM = "{\"show\":{\"title\":\"How I Met Your Mother\",\"year\":2005,\"url\":\"http://trakt.tv/show/how-i-met-your-mother\",\"first_aired\":1127185200,\"first_aired_iso\":\"2005-09-19T20:00:00-04:00\",\"first_aired_utc\":1127174400,\"country\":\"United States\",\"overview\":\"The year is 2030. Ted Mosby is relaying the story of how he met his wife to his daughter and son. The story starts in the year 2005, when then twenty-seven year old architect Ted was spurred on to want to get married after his best friends from his college days at Wesleyan, lawyer Marshall Eriksen, who was his roommate at the time and kindergarten teacher Lily Aldrin, got engaged after nine years of dating each other. Ted's new quest in life was much to the dismay of his womanizing friend, Barney Stinson. But soon after Marshall and Lily's engagement, Ted believed that his life mate was going to be news reporter and aspiring news anchor Robin Scherbatsky, who, despite having had a romantic relationship with her after this time, ended up being who the kids know as their \\\"Aunt\\\" Robin. As Ted relays the story to his kids, the constants are that their Uncle Marshall, Aunt Lily, Uncle Barney and Aunt Robin are always in the picture and thus have something to do with how he got together with their mother.\",\"runtime\":30,\"network\":\"CBS\",\"air_day\":\"Monday\",\"air_time\":\"8:00pm\",\"certification\":\"TV-PG\",\"imdb_id\":\"tt0460649\",\"tvdb_id\":75760,\"tvrage_id\":3918,\"images\":{\"poster\":\"http://slurm.trakt.us/images/posters/48.46.jpg\",\"fanart\":\"http://slurm.trakt.us/images/fanart/48.46.jpg\",\"banner\":\"http://slurm.trakt.us/images/banners/48.46.jpg\"},\"ratings\":{\"percentage\":85,\"votes\":17855,\"loved\":16910,\"hated\":945},\"genres\":[\"Comedy\",\"Romance\"]},\"episode\":{\"season\":3,\"number\":6,\"tvdb_id\":336998,\"imdb_id\":\"\",\"title\":\"I'm Not That Guy\",\"overview\":\"Marshall has to contemplate giving up his plan of using his law degree to help the planet when he's courted by a major firm; Lily reveals a secret to Robin that not even Marshall knows; Ted discovers a porn actor is using his name\",\"url\":\"http://trakt.tv/show/how-i-met-your-mother/season/3/episode/6\",\"first_aired\":1193713200,\"first_aired_iso\":\"2007-10-29T20:00:00-04:00\",\"first_aired_utc\":1193702400,\"images\":{\"screen\":\"http://slurm.trakt.us/images/episodes/48-3-6.46.jpg\"},\"ratings\":{\"percentage\":83,\"votes\":335,\"loved\":325,\"hated\":10}}}";

    @Mock
    private HttpClient defaultHttpClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        instance.setHttpClient(defaultHttpClient);
    }

    @Test
    public void testGetEpisodeByTitleAndEpisode() throws IOException {
        HttpResponseFactory factory = new DefaultHttpResponseFactory();
        HttpResponse response = factory.newHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null), null);
        response.setEntity(new StringEntity(episodeHIMYM, "UTF-8"));
        Mockito.when(defaultHttpClient.execute(getRequest("http://api.trakt.tv/show/episode/summary.json/" + Constants.TRAKT_API_KEY + "/how-i-met-your-mother/" + 3 + "/" + 6))).thenReturn(response);
        TraktEpisodeResponse r = instance.getBySeasonEpisode("how i met your mother", 3, 6);
        
        
        assertEquals(r.getEpisode().getSeason(), 3);
        assertEquals(r.getEpisode().getNumber(), 6);
    }
    
    private HttpGetWithEquals getRequest(String url){
        HttpGetWithEquals request = new HttpGetWithEquals(url);
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        request.addHeader("Accept-Language", "sv-SE");
        request.addHeader("Accept", "application/json");
        return request;
    }
    
}
