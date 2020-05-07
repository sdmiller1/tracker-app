package com.movieapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.utilities.PropertiesLoader;
import com.omdb.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

/**
 * The Data Access Object for interacting with OMDB
 */
public class OMDBDao implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Properties properties;

    private GenericDao<com.movieapp.model.Movie> movieDao = new GenericDao<>(com.movieapp.model.Movie.class);

    /**
     * Instantiates a new Omdb dao.
     */
    public OMDBDao() {
        properties = loadProperties("/application.properties");
    }

    /**
     * Gets movie by title.
     *
     * @param title the title
     * @return an OMDB movie object
     */
    public Movie getMovieByTitle(String title) {
        title = title.replace(" ", "+");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(properties.getProperty("omdb-url") +  properties.getProperty("omdb-api-key") + "&t=" + title);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Movie movie = null;
        try {
            movie = mapper.readValue(response, Movie.class);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return movie;
    }

    /**
     * Add movie to database
     *
     * @param title the title of the movie to be added
     * @return the primary key id of the movie that was just added
     */
    public int addMovieToDatabase(String title) {
        Movie apiResult = getMovieByTitle(title);
        com.movieapp.model.Movie movie = new com.movieapp.model.Movie();

        int id = 0;

        if (apiResult.getTitle() != null) {
            movie.setTitle(apiResult.getTitle());
            movie.setImdbId(apiResult.getImdbID());
            movie.setImage(apiResult.getPoster());

            try {
                String runtime = apiResult.getRuntime();
                runtime = runtime.substring(0, runtime.length() - 4);
                movie.setRuntime(Integer.parseInt(runtime));
            } catch (Exception e) {
                movie.setRuntime(0);
            }

            movie.setRatingMPAA(apiResult.getRated());
            movie.setReleaseYear(apiResult.getYear());
            movie.setPlot(apiResult.getPlot());
            movie.setGenre(apiResult.getGenre());

            id = movieDao.insert(movie);
        }

        return id;
    }
}
