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
}