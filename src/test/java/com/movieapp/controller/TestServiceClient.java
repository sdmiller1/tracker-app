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
        Movie movie = new OMDBDao().getMovieByTitle("The Martian");

        assertEquals("The Martian", movie.getTitle());
    }
}