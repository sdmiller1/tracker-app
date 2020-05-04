package com.movieapp.controller;

import com.movieapp.model.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the OMDB API
 */
public class OMDBDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private OMDBDao dao = new OMDBDao();
    private GenericDao<Movie> genericDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        genericDao = new GenericDao<>(Movie.class);
    }

    /**
     * Test api for response.
     */
    @Test
    public void testAPIForResponse() {
        com.omdb.Movie movie = dao.getMovieByTitle("The Martian");

        assertEquals("The Martian", movie.getTitle());
    }

    /**
     * Add movie to db from api.
     */
    @Test
    public void addMovieToDBFromAPI() {
        String title = "Interstellar";

        dao.addMovieToDatabase(title);

        List<Movie> movies = genericDao.findByPropertyEqual("title", "The Martian");

        assertEquals(movies.size(), 1);
    }
}