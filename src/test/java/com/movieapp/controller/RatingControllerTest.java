package com.movieapp.controller;

import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for the RatingController
 */
public class RatingControllerTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<User> genericDao;
    private RatingController ratingController = new RatingController();

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        genericDao = new GenericDao<>(User.class);
    }

    /**
     * Rate a movie
     */
    @Test
    void rateAMovie() {
        String imdbId = "tt3659388";
        String username = "astrodoug";
        int score = 5;

        String message = ratingController.rateMovie(imdbId, username, score);

        assertEquals("Successfully Rated", message);
    }

}
