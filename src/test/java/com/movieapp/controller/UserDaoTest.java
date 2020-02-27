package com.movieapp.controller;

import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class UserDaoTest {

    GenericDao<User> dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<User>(User.class);
    }

    @Test
    void getById() {
        User user = dao.getById(1);
        assertEquals(1, user.getId());
    }

    @Test
    void saveOrUpdate() {
        User user = dao.getById(1);
        user.setFirstName("Matt");
        user.setLastName("Damon");
        dao.saveOrUpdate(user);
        assertEquals(user, dao.getById(1));
    }

    @Test
    void insert() {
        User user = new User("Brad", "Pitt", "movieGuy12", "password", null);
        int id = dao.insert(user);
        assertEquals(user, dao.getById(id));
    }

//    @Test
//    void insertWithRating() {
//        User user = new User();
//        user.setUsername("Matt Damon");
//
//        Rating rating = new Rating("Ad Astra", 4, user);
//
//        user.addRating(rating);
//
//        int id = dao.insert(user);
//        assertEquals("Matt Damon", dao.getById(id).getUsername());
//        assertEquals(1, dao.getById(id).getRatings().size());
//    }
//
//    @Test
//    void delete() {
//        User user = dao.getById(1);
//        dao.delete(user);
//        assertNull(dao.getById(1));
//    }
//
//    @Test
//    void getAll() {
//        List<User> users = dao.getAll();
//        assertEquals(4, users.size());
//    }
}
