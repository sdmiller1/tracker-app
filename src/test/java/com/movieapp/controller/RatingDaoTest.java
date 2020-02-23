package com.movieapp.controller;

import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RatingDaoTest {

    RatingDao dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new RatingDao();
    }

    @Test
    void getById() {
        Rating rating = dao.getById(1);
        assertEquals(1, rating.getId());
    }

    @Test
    void saveOrUpdate() {
        Rating rating = dao.getById(1);
        rating.setRating(1);
        dao.saveOrUpdate(rating);
        assertEquals(1, dao.getById(1).getRating());
    }

    @Test
    void insert() {
        User user = new UserDao().getById(1);
        Rating rating = new Rating("Interstellar", 5, user);

        int id = dao.insert(rating);
        assertEquals("Interstellar", dao.getById(id).getMovie());
        assertEquals("bob", dao.getById(id).getUser().getUsername());
    }

    @Test
    void delete() {
        Rating rating = dao.getById(5);
        dao.delete(rating);
        assertNull(dao.getById(5));
    }

    @Test
    void getAll() {
        List<Rating> ratings = dao.getAll();
        assertEquals(5, ratings.size());
    }
}