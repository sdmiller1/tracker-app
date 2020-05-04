package com.movieapp.controller;

import com.movieapp.model.Movie;
import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RatingDaoTest {

    GenericDao<Rating> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<Rating>(Rating.class);
    }

    @Test
    void getById() {
        Rating rating = (Rating)dao.getById(1);
        assertEquals(1, rating.getId());
        assertEquals(5, rating.getRating());
    }

    @Test
    void saveOrUpdate() {
        Rating rating = (Rating)dao.getById(1);
        rating.setRating(1);
        dao.saveOrUpdate(rating);
        Rating updatedRating = (Rating)dao.getById(1);
        assertEquals(rating, updatedRating);
    }

    @Test
    void insert() {
        User user = new GenericDao<User>(User.class).getById(1);
        Movie movie = new GenericDao<Movie>(Movie.class).getById(2);
        Rating rating = new Rating(user, movie, "2020-02-25", 4);

        int id = dao.insert(rating);
        assertEquals(rating, dao.getById(id));
    }

    @Test
    void delete() {
        Rating rating = dao.getById(5);
        dao.delete(rating);
        assertNull(dao.getById(5));

        //Verify that user is not deleted
        GenericDao<User> userGenericDao = new GenericDao<>(User.class);
        assertNotNull(userGenericDao.getById(1));
    }

    @Test
    void getAll() {
        List<Rating> ratings = dao.getAll();
        assertEquals(5, ratings.size());
    }

    @Test
    void getRatingsByPropertyEquals() {
        List<Rating> ratings = dao.findByPropertyEqual("rating", 5);
        assertEquals(4, ratings.size());
    }

    @Test
    void getRatingsByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("rating", 5);

        List<Rating> ratings = dao.findByPropertyEqual(propertiesMap);
        assertEquals(4, ratings.size());
    }
}