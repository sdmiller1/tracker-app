package com.movieapp.controller;

import com.movieapp.model.Movie;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieSearcherTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<Movie> genericDao;
    private MovieSearcher movieSearcher = new MovieSearcher();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        genericDao = new GenericDao<>(Movie.class);
    }

    @Test
    void findByTitleInDB() {
        List<Movie> movies = movieSearcher.findByTitle("The Mar");

        // The Martian
        Movie movie = genericDao.getById(1);

        assertEquals(1, movies.size());
        assertEquals(movie, movies.get(0));
    }

    @Test
    void findByTitleNotInDB() {
        List<Movie> movies = movieSearcher.findByTitle("Interstellar");

        assertEquals(1, movies.size());

        Movie movie = movies.get(0);

        assertEquals("2014", movie.getReleaseYear());

        Movie movieInDB = genericDao.getById(movie.getId());

        assertEquals(movie, movieInDB);
    }

    @Test
    void findByImdbID() {
        Movie movie = movieSearcher.findById("tt3659388");

        assertEquals("The Martian", movie.getTitle());
//        TODO: what should return if the movie is not found
    }

    @Test
    void findByCollectionId() {
        List<Movie> movies = movieSearcher.findByCollectionId(1);

        assertEquals(3, movies.size());
    }
}
