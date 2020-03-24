package com.movieapp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.log.MLogger;
import com.movieapp.controller.GenericDao;
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
public class MovieData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @GET
    @Produces("application/json")
    public Response getMovies() {
        List<Movie> movies = new GenericDao<Movie>(Movie.class).getAll();

        ObjectMapper mapper = new ObjectMapper();

        String jsonOutput = "oops";

        try {
            jsonOutput = mapper.writeValueAsString(movies);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return Response.status(200).entity(jsonOutput).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{param}")
    public Response getMovieByTitle(@PathParam("param") String title) {


        List<Movie> movies = new GenericDao<Movie>(Movie.class).findByPropertyEqual("title", title);

        ObjectMapper mapper = new ObjectMapper();

        String jsonOutput = "oops";

        try {
            jsonOutput = mapper.writeValueAsString(movies);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return Response.status(200).entity(jsonOutput).build();
    }
}