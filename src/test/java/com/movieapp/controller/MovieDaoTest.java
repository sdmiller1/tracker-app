package com.movieapp.controller;

import com.movieapp.model.*;
import com.movieapp.model.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for the Movie class
 */
class MovieDaoTest {

    private GenericDao<Movie> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<Movie>(Movie.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        Movie movie = (Movie)dao.getById(1);
        assertEquals(1, movie.getId());
        assertEquals("The Martian", movie.getTitle());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        Movie movie = (Movie)dao.getById(1);
        movie.setTitle("Interstellar");
        dao.saveOrUpdate(movie);
        Movie updatedMovie = (Movie)dao.getById(1);
        assertEquals(movie, updatedMovie);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        Set<Collection> collections = new HashSet<>();
        collections.add(new GenericDao<Collection>(Collection.class).getById(1));
        Movie movie = new Movie("tt1327489", "Interstellar", "https://", 155, "PG-13", "", "", "");

        int id = dao.insert(movie);
        assertEquals(movie, dao.getById(id));
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        Movie movie = dao.getById(1);
        dao.delete(movie);
        assertNull(dao.getById(1));

        // verify that ratings are deleted along with the movie
        GenericDao<Rating> ratingGenericDao = new GenericDao<>(Rating.class);
        assertNull(ratingGenericDao.getById(1));

        // Verify the movie was removed from collections
        GenericDao<MovieCollection> movieCollectionGenericDao = new GenericDao<>(MovieCollection.class);
        assertNull(movieCollectionGenericDao.getById(1));
        assertNull(movieCollectionGenericDao.getById(4));
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<Movie> movies = dao.getAll();
        assertEquals(3, movies.size());
    }

    /**
     * Gets movies by property equals.
     */
    @Test
    void getMoviesByPropertyEquals() {
        List<Movie> movies = dao.findByPropertyEqual("title", "The Martian");
        assertEquals(1, movies.size());
    }

    /**
     * Gets movies by property equals map.
     */
    @Test
    void getMoviesByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("title", "The Martian");

        List<Movie> movies = dao.findByPropertyEqual(propertiesMap);
        assertEquals(1, movies.size());
    }

    /**
     * Gets movies by property like.
     */
    @Test
    void getMoviesByPropertyLike() {
        List<Movie> movies = dao.findByPropertyLike("title", "e");
        assertEquals(2, movies.size());
    }
}