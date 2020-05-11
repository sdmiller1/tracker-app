package com.movieapp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.controller.GenericDao;
import com.movieapp.controller.MovieSearcher;
import com.movieapp.model.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
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

    @DELETE
    @Produces("text/plain")
    @Path("/imdbid={param}")
    public Response deleteMovieFromDB(@PathParam("param") String imdbid) {
        GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);

        Movie movie = new MovieSearcher().findById(imdbid);

        String response = "Unable to delete movie";

        try {
            movieGenericDao.delete(movie);

            Movie deletedMovie = new MovieSearcher().findById(imdbid);

            if (deletedMovie == null) {
                response = "Successfully Deleted the Movie";
            }
        } catch (Exception e) {
            logger.error(e);
        }


        return Response.status(200).entity(response).build();
    }
}