package com.movieapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omdb.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class OMDBDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<com.movieapp.model.Movie> movieDao = new GenericDao<>(com.movieapp.model.Movie.class);

    public Movie getMovieByTitle(String title) {
        title = title.replaceAll(" ", "+");

        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://www.omdbapi.com/?apikey=a59d3c7e&t=" + title);
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

    public int addMovieToDatabase(String title) {
        Movie apiResult = getMovieByTitle(title);
        com.movieapp.model.Movie movie = new com.movieapp.model.Movie();

        int id = 0;

        if (apiResult.getTitle() != null) {
            movie.setTitle(apiResult.getTitle());
            movie.setImdbId(apiResult.getImdbID());
            movie.setImage(apiResult.getPoster());

            String runtime = apiResult.getRuntime();
            runtime = runtime.substring(0, runtime.length() - 4);
            movie.setRuntime(Integer.parseInt(runtime));

            movie.setRatingMPAA(apiResult.getRated());
            movie.setReleaseYear(apiResult.getYear());
            movie.setPlot(apiResult.getPlot());
            movie.setGenre(apiResult.getGenre());

            id = movieDao.insert(movie);
        }

        return id;
    }
}
