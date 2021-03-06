package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.MovieCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MovieCollection class
 */
class MovieCollectionDaoTest {

    private GenericDao<MovieCollection> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<MovieCollection>(MovieCollection.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        MovieCollection myMovie = (MovieCollection)dao.getById(1);
        assertEquals(1, myMovie.getId());
        assertTrue(myMovie.isHasDvd());
        assertFalse(myMovie.isHas4k());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        MovieCollection myMovie = (MovieCollection)dao.getById(1);
        myMovie.setMovie(new GenericDao<Movie>(Movie.class).getById(3));
        dao.saveOrUpdate(myMovie);
        MovieCollection myNewMovie = (MovieCollection)dao.getById(1);
        assertEquals(myMovie, myNewMovie);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        Movie movie = new GenericDao<Movie>(Movie.class).getById(3);

        Collection collection = new GenericDao<Collection>(Collection.class).getById(2);

        MovieCollection myNewMovie = new MovieCollection(collection, movie, true, true, false);

        int id = dao.insert(myNewMovie);
        assertEquals(myNewMovie, dao.getById(id));
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        MovieCollection movieToDeleteFromCollection = dao.getById(3);
        dao.delete(movieToDeleteFromCollection);
        assertNull(dao.getById(3));
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<MovieCollection> movieCollections = dao.getAll();
        assertEquals(4, movieCollections.size());
    }

    /**
     * Gets MovieCollection by property equals.
     */
    @Test
    void getMovieCollectionByPropertyEquals() {
        List<MovieCollection> movieCollections = dao.findByPropertyEqual("hasDvd", true);
        assertEquals(4, movieCollections.size());
    }

    /**
     * Gets MovieCollection by property equals map.
     */
    @Test
    void getMovieCollectionByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("hasDvd", true);
        propertiesMap.put("has4k", false);

        List<MovieCollection> movieCollections = dao.findByPropertyEqual(propertiesMap);
        assertEquals(2, movieCollections.size());
    }

}