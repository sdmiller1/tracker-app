package com.movieapp.controller;

import com.movieapp.model.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MovieSearcher class
 */
public class MovieSearcherTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<Movie> genericDao;
    private MovieSearcher movieSearcher = new MovieSearcher();

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
     * Find movies by title.
     */
    @Test
    void findMoviesByTitleInDB() {
        List<Movie> movies = movieSearcher.findByTitle("The Mar");

        // The Martian
        Movie movie = genericDao.getById(1);

        assertEquals(1, movies.size());
        assertEquals(movie, movies.get(0));
    }

    /**
     * Find movies by title but the movie is not in the db yet.
     */
    @Test
    void findMoviesByTitleNotInDB() {
        List<Movie> movies = movieSearcher.findByTitle("Interstellar");

        assertEquals(1, movies.size());

        Movie movie = movies.get(0);

        assertEquals("2014", movie.getReleaseYear());

        Movie movieInDB = genericDao.getById(movie.getId());

        assertEquals(movie, movieInDB);
    }

    /**
     * Find movies by imdb id.
     */
    @Test
    void findMoviesByImdbID() {
        Movie movie = movieSearcher.findById("tt3659388");

        assertEquals("The Martian", movie.getTitle());
    }

    /**
     * Find movies by imdb id but no results.
     */
    @Test
    void findMoviesByImdbIDNoResults() {
        Movie movie = movieSearcher.findById("wrong id");

        assertNull(movie);
    }

    /**
     * Find movies by collection id.
     */
    @Test
    void findMoviesByCollectionId() {
        List<Movie> movies = movieSearcher.findByCollectionId(1);

        assertEquals(3, movies.size());
    }
}
