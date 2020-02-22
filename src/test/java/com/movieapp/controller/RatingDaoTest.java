package com.movieapp.controller;

import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}