package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.MovieCollection;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionDaoTest {

    GenericDao<MovieCollection> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<MovieCollection>(MovieCollection.class);
    }

    @Test
    void getById() {
        MovieCollection myMovie = (MovieCollection)dao.getById(1);
        assertEquals(1, myMovie.getId());
        assertTrue(myMovie.isHasDvd());
        assertFalse(myMovie.isHas4k());
    }

    @Test
    void saveOrUpdate() {
        MovieCollection myMovie = (MovieCollection)dao.getById(1);
        myMovie.setMovie(new GenericDao<Movie>(Movie.class).getById(3));
        dao.saveOrUpdate(myMovie);
        MovieCollection myNewMovie = (MovieCollection)dao.getById(1);
        assertEquals(myMovie, myNewMovie);
    }

    @Test
    void insert() {
        // TODO Test this: if the movie is not already in the db it needs to be inserted before being added to movieCollection
        // Movie movie = new Movie(7, "Solaris");
        Movie movie = new GenericDao<Movie>(Movie.class).getById(3);

        Collection collection = new GenericDao<Collection>(Collection.class).getById(2);

        MovieCollection myNewMovie = new MovieCollection(collection, movie, true, true, false);

        int id = dao.insert(myNewMovie);
        assertEquals(myNewMovie, dao.getById(id));
    }

    @Test
    void delete() {
        MovieCollection movieToDeleteFromCollection = dao.getById(3);
        dao.delete(movieToDeleteFromCollection);
        assertNull(dao.getById(3));
    }

    @Test
    void getAll() {
        List<MovieCollection> movieCollections = dao.getAll();
        assertEquals(4, movieCollections.size());
    }

    @Test
    void getRatingsByPropertyEquals() {
        List<MovieCollection> movieCollections = dao.findByPropertyEqual("hasDvd", true);
        assertEquals(4, movieCollections.size());
    }

    @Test
    void getRatingsByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("hasDvd", true);
        propertiesMap.put("has4k", false);

        List<MovieCollection> movieCollections = dao.findByPropertyEqual(propertiesMap);
        assertEquals(2, movieCollections.size());
    }
}