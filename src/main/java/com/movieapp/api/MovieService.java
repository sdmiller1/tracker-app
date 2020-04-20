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

@Path("/movies")
public class MovieService {

    private final Logger logger = LogManager.getLogger(this.getClass());

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

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonOutput).build();
    }

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

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonOutput).build();
    }

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

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonOutput).build();
    }

//  TODO: this method is just for testing right now
    @GET
    @Produces("application/json")
    @Path("/collection={param}")
    public Response getMoviesFromCollection(@PathParam("param") String collection) {

        int collectionId = Integer.parseInt(collection);

        List<Movie> movies = new MovieSearcher().findByCollectionId(collectionId);

        ObjectMapper mapper = new ObjectMapper();

        String jsonOutput = "An Error Occurred";

        try {
            jsonOutput = mapper.writeValueAsString(movies);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonOutput).build();
    }
}