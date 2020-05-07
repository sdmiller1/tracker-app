package com.movieapp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.controller.GenericDao;
import com.movieapp.controller.MovieSearcher;
import com.movieapp.model.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Service used to get movie information
 */
@Path("/movies")
public class MovieService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets movies.
     *
     * @return the movies
     */
    @GET
    @Produces("application/json")
    public Response getMovies() {
        List<Movie> movies = new GenericDao<>(Movie.class).getAll();

        ObjectMapper mapper = new ObjectMapper();

        String jsonOutput = "An error occurred";

        try {
            jsonOutput = mapper.writeValueAsString(movies);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return Response.status(200).entity(jsonOutput).build();
    }

    /**
     * Gets movie by title.
     *
     * @param title the title
     * @return the movie by title
     */
    @GET
    @Produces("application/json")
    @Path("/title={param}")
    public Response getMovieByTitle(@PathParam("param") String title) {

        List<Movie> movies = new MovieSearcher().findByTitle(title);

        ObjectMapper mapper = new ObjectMapper();

        String jsonOutput = "An error occurred";

        try {
            jsonOutput = mapper.writeValueAsString(movies);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return Response.status(200).entity(jsonOutput).build();
    }

    /**
     * Gets movie by imdb id.
     *
     * @param imdbid the imdbid
     * @return the movie by imdb id
     */
    @GET
    @Produces("application/json")
    @Path("/imdbid={param}")
    public Response getMovieByImdbId(@PathParam("param") String imdbid) {

        Movie movie = new MovieSearcher().findById(imdbid);

        ObjectMapper mapper = new ObjectMapper();

        String jsonOutput = "An error occurred";

        try {
            jsonOutput = mapper.writeValueAsString(movie);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return Response.status(200).entity(jsonOutput).build();
    }
}