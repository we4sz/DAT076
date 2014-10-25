package edu.chalmers.dat076.moviefinder.controller;

import edu.chalmers.dat076.moviefinder.model.Range;
import edu.chalmers.dat076.moviefinder.persistence.Episode;
import edu.chalmers.dat076.moviefinder.persistence.EpisodeRepository;
import edu.chalmers.dat076.moviefinder.persistence.EpisodeSpecs;
import edu.chalmers.dat076.moviefinder.persistence.Movie;
import edu.chalmers.dat076.moviefinder.persistence.MovieRepository;
import edu.chalmers.dat076.moviefinder.persistence.MovieSpecs;
import edu.chalmers.dat076.moviefinder.persistence.Series;
import edu.chalmers.dat076.moviefinder.persistence.SeriesRepository;
import edu.chalmers.dat076.moviefinder.persistence.SeriesSpecs;
import edu.chalmers.dat076.moviefinder.utils.FileControllerUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for the api/files endpoint.
 *
 * @author John
 */
@Controller
@RequestMapping("api/files")
public class FileController {

    @Autowired
    MovieRepository movieRepository;
    
    @Autowired
    SeriesRepository seriesRepository;
    
    @Autowired
    EpisodeRepository episodeRepository;

    /**
     * Method for accessing Movies. If no params defined It will return the first
     * 25 movies that it finds.
     *
     * @param imdbRating Only movies with a rating equal or above imdbRating
     * returned
     * @param runtime Only movies with a runtime equal or above runtime returned
     * @param releaseYear Only movies released releaseYear returned
     * @param page What page to return. Page 1 is indexed as 0
     * @param sort Database field to sort by. Default it is set as descending
     * @param asc Set true and sorting will be done ascending instead of
     * descending.
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Page<Movie> listMovies(
            @RequestParam(value = "imdbRating", required = false) Double imdbRating,
            @RequestParam(value = "runtime", required = false) Integer runtime,
            @RequestParam(value = "releaseYear", required = false) Integer releaseYear,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "asc", required = false) Boolean asc
    ) {
        
        Specifications<Movie> filter = where(null);
        
        if (imdbRating != null) {
            filter = filter.and(MovieSpecs.hasImdbRatingAbove(imdbRating));
        }
        if (runtime != null) {
            filter = filter.and(MovieSpecs.hasRuntimeAbove(runtime));
        }
        if (releaseYear != null){
            filter = filter.and(MovieSpecs.hasReleaseYear(releaseYear));
        }
        return movieRepository.findAll(filter, getPageRequest(page, sort, asc));
    }
    
    /**
     * Method for accessing Series. If no params defined It will return the first
     * 25 series that is found.
     * 
     * @param imdbRating Only Series with a rating equal or above imdbRating returned
     * @param releaseYear Only Series released releaseYear returned
     * @param page What page to return. Page 1 is indexed as 0. Default value is 0.
     * @param sort Database field to sort by. Default it is set as descending
     * @param asc Set true and sorting will be done ascending instead of descending.
     */
    @RequestMapping(value = "/series/", method = RequestMethod.GET)
    public @ResponseBody
    Page<Series> listSeries(
            @RequestParam(value = "imdbRating", required = false) Double imdbRating,
            @RequestParam(value = "releaseYear", required = false) Integer releaseYear,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "asc", required = false) Boolean asc
    ) {
        
        Specifications<Series> filter = where(null);

        if (imdbRating != null) {
            filter = filter.and(SeriesSpecs.hasImdbRatingAbove(imdbRating));
        }
        if (releaseYear != null){
            filter = filter.and(SeriesSpecs.hasReleaseYear(releaseYear));
        }
        return seriesRepository.findAll(filter, getPageRequest(page, sort, asc));
    }
    
    /**
     * Method for accessing Episodes. If no params defined It will return the first
     * 25 Episodes that is found.
     * 
     * @param imdbRating Only Episodes with a rating equal or above imdbRating returned
     * @param releaseYear Only Episodes released releaseYear returned
     * @param page What page to return. Page 1 is indexed as 0. Default value is 0.
     * @param sort Database field to sort by. Default it is set as descending
     * @param asc Set true and sorting will be done ascending instead of descending.
     */
    @RequestMapping(value = "/episodes/", method = RequestMethod.GET)
    public @ResponseBody
    Page<Episode> listEpisodes(
            @RequestParam(value = "imdbRating", required = false) Double imdbRating,
            @RequestParam(value = "releaseYear", required = false) Integer releaseYear,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "asc", required = false) Boolean asc
    ) {

        Specifications<Episode> filter = where(null);

        if (imdbRating != null) {
            filter = filter.and(EpisodeSpecs.hasImdbRatingAbove(imdbRating));
        }
        if (releaseYear != null){
            filter = filter.and(EpisodeSpecs.hasReleaseYear(releaseYear));
        }
        return episodeRepository.findAll(filter, getPageRequest(page, sort, asc));
    }
    
    /**
     * Creates a new PageRequest with what page to display and if possible set a
     * sorting method.
     */
    private PageRequest getPageRequest(Integer page, String sort, Boolean asc){
        Sort s = null;
        if (sort != null) {
            if (asc != null){
                if (asc){
                    s = new Sort(Sort.Direction.ASC, sort);
                } else {
                    s = new Sort(Sort.Direction.DESC, sort);
                }
            } else {
                s = new Sort(Sort.Direction.DESC, sort);
            }
        }
        PageRequest pageRequest;
        if (page != null){
                pageRequest = new PageRequest(page, 25, s);
        } else {
                pageRequest = new PageRequest(0, 25, s);
        }
        return pageRequest;
    }

    /**
     * Returns the movie with database id id.
     * 
     * @param id
     * @return  one Movie.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Movie getMovieById(@PathVariable long id) {
        return movieRepository.findOne(id);
    }
    
    /**
     * Returns the series with database id id.
     * 
     * @param id
     * @return one series with all its episode information.
     */
    @RequestMapping(value = "/series/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Series getSeriesById(@PathVariable long id) {
        return seriesRepository.findOne(id);
    }
    
    /**
     * Returns the Episode with database id id.
     * 
     * @param id
     * @return One episode.
     */
    @RequestMapping(value = "/episodes/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Episode getEpisodeById(@PathVariable long id) {
        return episodeRepository.findOne(id);
    }
    
    @RequestMapping(value = "/sub/{id}", method = RequestMethod.GET)
    public void getSubtitle(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Movie m = movieRepository.findOne(id);
        if (m != null) {
            try {
                File file = new File(m.getFilePath());
                byte[] bytes = new byte[64 * 1024];
                byte[] bytes2 = new byte[64 * 1024];
                long size = file.length();
                try (FileInputStream f = new FileInputStream(file)) {
                    f.read(bytes);
                    f.skip(size - 64 * 1024 * 2);
                    f.read(bytes2);
                    String hash = bytesToHex(MessageDigest.getInstance("MD5").digest(concat(bytes, bytes2)));
                    String url = "http://api.thesubdb.com/?action=download&hash=" + hash + "&language=en";
                    HttpClient client = HttpClientBuilder.create().build();
                    HttpGet getRequest = new HttpGet(url);
                    getRequest.addHeader("User-Agent", "SubDB/1.0 (MovieFinder/0.1; http://johanssonjohn.se)");
                    HttpResponse res = client.execute(getRequest);

                    if (res.getStatusLine().getStatusCode() == 200) {
                        String responseString = EntityUtils.toString(res.getEntity(), "UTF-8");
                        File temp = File.createTempFile("tempfile" + System.currentTimeMillis() + m.getId(), ".srt");
                        //write it
                        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                        bw.write(responseString);
                        bw.close();

                        processRequest(request, response, true, temp.toPath().toString(), "text/plain");
                    }
                }
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] concat(byte[] A, byte[] B) {
        int aLen = A.length;
        int bLen = B.length;
        byte[] C = new byte[aLen + bLen];
        System.arraycopy(A, 0, C, 0, aLen);
        System.arraycopy(B, 0, C, aLen, bLen);
        return C;
    }

    @RequestMapping(value = "/stream/{id}", method = RequestMethod.GET)
    public void getMovieStream(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Movie m = movieRepository.findOne(id);
        if (m == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        processRequest(request, response, true, m.getFilePath(), "video/mp4");
    }

    /**
     * Process the actual request.
     *
     * @param request The request to be processed.
     * @param response The response to be created.
     * @param content Whether the request body should be written (GET) or not
     * (HEAD).
     * @throws IOException If something fails at I/O level.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response, boolean content, String path, String defaultContentType)
            throws IOException {
        // Validate the requested file ------------------------------------------------------------

        // URL-decode the file name (might contain spaces and on) and prepare file object.
        File file = new File(path);

        // Check if file actually exists in filesystem.
        if (!file.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning page, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Prepare some variables. The ETag is an unique identifier of the file.
        String fileName = file.getName();
        long length = file.length();
        long lastModified = file.lastModified();
        String eTag = fileName + "_" + length + "_" + lastModified;
        long expires = System.currentTimeMillis() + FileControllerUtils.DEFAULT_EXPIRE_TIME;

        // Validate request headers for caching ---------------------------------------------------
        // If-None-Match header should contain "*" or ETag. If so, then return 304.
        String ifNoneMatch = request.getHeader("If-None-Match");
        if (ifNoneMatch != null && FileControllerUtils.matches(ifNoneMatch, eTag)) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            response.setHeader("ETag", eTag); // Required in 304.
            response.setDateHeader("Expires", expires); // Postpone cache with 1 week.
            return;
        }

        // If-Modified-Since header should be greater than LastModified. If so, then return 304.
        // This header is ignored if any If-None-Match header is specified.
        long ifModifiedSince = request.getDateHeader("If-Modified-Since");
        if (ifNoneMatch == null && ifModifiedSince != -1 && ifModifiedSince + 1000 > lastModified) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            response.setHeader("ETag", eTag); // Required in 304.
            response.setDateHeader("Expires", expires); // Postpone cache with 1 week.
            return;
        }

        // Validate request headers for resume ----------------------------------------------------
        // If-Match header should contain "*" or ETag. If not, then return 412.
        String ifMatch = request.getHeader("If-Match");
        if (ifMatch != null && !FileControllerUtils.matches(ifMatch, eTag)) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
            return;
        }

        // If-Unmodified-Since header should be greater than LastModified. If not, then return 412.
        long ifUnmodifiedSince = request.getDateHeader("If-Unmodified-Since");
        if (ifUnmodifiedSince != -1 && ifUnmodifiedSince + 1000 <= lastModified) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
            return;
        }

        // Validate and process range -------------------------------------------------------------
        // Prepare some variables. The full Range represents the complete file.
        Range full = new Range(0, length - 1, length);
        List<Range> ranges = new ArrayList<>();

        // Validate and process Range and If-Range headers.
        String range = request.getHeader("Range");
        if (range != null) {

            // Range header should match format "bytes=n-n,n-n,n-n...". If not, then return 416.
            if (!range.matches("^bytes=\\d*-\\d*(,\\d*-\\d*)*$")) {
                response.setHeader("Content-Range", "bytes */" + length); // Required in 416.
                response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return;
            }

            // If-Range header should either match ETag or be greater then LastModified. If not,
            // then return full file.
            String ifRange = request.getHeader("If-Range");
            if (ifRange != null && !ifRange.equals(eTag)) {
                try {
                    long ifRangeTime = request.getDateHeader("If-Range"); // Throws IAE if invalid.
                    if (ifRangeTime != -1 && ifRangeTime + 1000 < lastModified) {
                        ranges.add(full);
                    }
                } catch (IllegalArgumentException ignore) {
                    ranges.add(full);
                }
            }

            // If any valid If-Range header, then process each part of byte range.
            if (ranges.isEmpty()) {
                for (String part : range.substring(6).split(",")) {
                    // Assuming a file with length of 100, the following examples returns bytes at:
                    // 50-80 (50 to 80), 40- (40 to length=100), -20 (length-20=80 to length=100).
                    long start = FileControllerUtils.sublong(part, 0, part.indexOf("-"));
                    long end = FileControllerUtils.sublong(part, part.indexOf("-") + 1, part.length());

                    if (start == -1) {
                        start = length - end;
                        end = length - 1;
                    } else if (end == -1 || end > length - 1) {
                        end = length - 1;
                    }

                    // Check if Range is syntactically valid. If not, then return 416.
                    if (start > end) {
                        response.setHeader("Content-Range", "bytes */" + length); // Required in 416.
                        response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                        return;
                    }

                    // Add range.
                    ranges.add(new Range(start, end, length));
                }
            }
        }

        // Prepare and initialize response --------------------------------------------------------
        // Get content type by file name and set default GZIP support and content disposition.
        String contentType = request.getServletContext().getMimeType(fileName);
        boolean acceptsGzip = false;
        String disposition = "inline";

        // If content type is unknown, then set the default value.
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        // To add new content types, add new mime-mapping entry in web.xml.
        //if (contentType == null) {
            contentType = defaultContentType;
        //}

        // If content type is text, then determine whether GZIP content encoding is supported by
        // the browser and expand content type with the one and right character encoding.
        if (contentType.startsWith("text")) {
            String acceptEncoding = request.getHeader("Accept-Encoding");
            acceptsGzip = acceptEncoding != null && FileControllerUtils.accepts(acceptEncoding, "gzip");
            contentType += ";charset=UTF-8";
        } // Else, expect for images, determine content disposition. If content type is supported by
        // the browser, then set to inline, else attachment which will pop a 'save as' dialogue.
        else if (!contentType.startsWith("image")) {
            String accept = request.getHeader("Accept");
            disposition = accept != null && FileControllerUtils.accepts(accept, contentType) ? "inline" : "attachment";
        }

        // Initialize response.
        response.reset();
        response.setBufferSize(FileControllerUtils.DEFAULT_BUFFER_SIZE);
        //response.setHeader("Content-Disposition", disposition + ";filename=\"" + fileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("ETag", eTag);
        response.setDateHeader("Last-Modified", lastModified);
        response.setDateHeader("Expires", expires);

        // Send requested file (part(s)) to client ------------------------------------------------
        // Prepare streams.
        RandomAccessFile input = null;
        OutputStream output = null;

        try {
            // Open streams.
            input = new RandomAccessFile(file, "r");
            output = response.getOutputStream();

            if (ranges.isEmpty() || ranges.get(0) == full) {

                // Return full file.
                Range r = full;
                response.setContentType(contentType);
                response.setHeader("Content-Range", "bytes " + r.start + "-" + r.end + "/" + r.total);

                if (content) {
                    if (acceptsGzip) {
                        // The browser accepts GZIP, so GZIP the content.
                        response.setHeader("Content-Encoding", "gzip");
                        output = new GZIPOutputStream(output, FileControllerUtils.DEFAULT_BUFFER_SIZE);
                    } else {
                        // Content length is not directly predictable in case of GZIP.
                        // So only add it if there is no means of GZIP, else browser will hang.
                        response.setHeader("Content-Length", String.valueOf(r.length));
                    }

                    // Copy full range.
                    FileControllerUtils.copy(input, output, r.start, r.length);
                }

            } else if (ranges.size() == 1) {

                // Return single part of file.
                Range r = ranges.get(0);
                response.setContentType(contentType);
                response.setHeader("Content-Range", "bytes " + r.start + "-" + r.end + "/" + r.total);
                response.setHeader("Content-Length", String.valueOf(r.length));
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

                if (content) {
                    // Copy single part range.
                    FileControllerUtils.copy(input, output, r.start, r.length);
                }

            } else {

                // Return multiple parts of file.
                response.setContentType("multipart/byteranges; boundary=" + FileControllerUtils.MULTIPART_BOUNDARY);
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

                if (content) {
                    // Cast back to ServletOutputStream to get the easy println methods.
                    ServletOutputStream sos = (ServletOutputStream) output;

                    // Copy multi part range.
                    for (Range r : ranges) {
                        // Add multipart boundary and header fields for every range.
                        sos.println();
                        sos.println("--" + FileControllerUtils.MULTIPART_BOUNDARY);
                        sos.println("Content-Type: " + contentType);
                        sos.println("Content-Range: bytes " + r.start + "-" + r.end + "/" + r.total);

                        // Copy single part range of multi part range.
                        FileControllerUtils.copy(input, output, r.start, r.length);
                    }

                    // End with multipart boundary.
                    sos.println();
                    sos.println("--" + FileControllerUtils.MULTIPART_BOUNDARY + "--");
                }
            }
        } finally {
            // Gently close streams.
            FileControllerUtils.close(output);
            FileControllerUtils.close(input);
        }
    }

}
