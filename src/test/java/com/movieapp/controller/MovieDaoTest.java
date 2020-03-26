package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MovieDaoTest {

    GenericDao<Movie> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<Movie>(Movie.class);
    }

    @Test
    void getById() {
        Movie movie = (Movie)dao.getById(1);
        assertEquals(1, movie.getId());
        assertEquals("The Martian", movie.getTitle());
    }

    @Test
    void saveOrUpdate() {
        Movie movie = (Movie)dao.getById(1);
        movie.setTitle("Interstellar");
        dao.saveOrUpdate(movie);
        Movie updatedMovie = (Movie)dao.getById(1);
        assertEquals(movie, updatedMovie);
    }

    @Test
    void insert() {
        Set<Collection> collections = new HashSet<>();
        collections.add(new GenericDao<Collection>(Collection.class).getById(1));
        Movie movie = new Movie(4, "tt1327489", "Interstellar", "https://", 155, "PG-13", "", "", "");

        int id = dao.insert(movie);
        assertEquals(movie, dao.getById(id));
    }

    @Test
    void delete() {
        Movie movie = dao.getById(3);
        dao.delete(movie);
        assertNull(dao.getById(3));
    }

    @Test
    void getAll() {
        List<Movie> movies = dao.getAll();
        assertEquals(3, movies.size());
    }

    @Test
    void getRatingsByPropertyEquals() {
        List<Movie> movies = dao.findByPropertyEqual("title", "The Martian");
        assertEquals(1, movies.size());
    }

    @Test
    void getRatingsByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("title", "The Martian");

        List<Movie> movies = dao.findByPropertyEqual(propertiesMap);
        assertEquals(1, movies.size());
    }

    @Test
    void getMovieByPropertyLike() {
        List<Movie> movies = dao.findByPropertyLike("title", "e");
        assertEquals(2, movies.size());
    }
}