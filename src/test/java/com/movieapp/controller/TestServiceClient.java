package com.movieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omdb.Movie;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceClient {

    @Test
    public void testOMDBJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://www.omdbapi.com/?apikey=a59d3c7e&t=The+Martian");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Movie movie = mapper.readValue(response, Movie.class);

//        assertEquals("???", response);
        assertEquals("The Martian", movie.getTitle());
    }
}