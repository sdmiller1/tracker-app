package com.movieapp.controller;

import com.movieapp.model.Movie;
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
        User user = new User("Brad", "Pitt", "movieGuy12", "password", true, true, true, new HashSet<Rating>());
        int id = dao.insert(user);
        assertEquals(user, dao.getById(id));
    }

    @Test
    void insertWithRating() {
        User user = new User("Brad", "Pitt", "movieGuy12", "password", true, true, true, new HashSet<Rating>());
        Movie movie = new GenericDao<Movie>(Movie.class).getById(2);

        Rating rating = new Rating(user, movie, "2020-02-25", 4);

        user.addRating(rating);

        int id = dao.insert(user);
        assertEquals(user, dao.getById(id));
    }

    @Test
    void delete() {
        User user = dao.getById(1);
        dao.delete(user);
        assertNull(dao.getById(1));
    }

    @Test
    void getAll() {
        List<User> users = dao.getAll();
        assertEquals(4, users.size());
    }

    @Test
    void getRatingsByPropertyEquals() {
        List<User> users = dao.findByPropertyEqual("password", "password");
        assertEquals(4, users.size());
    }

    @Test
    void getRatingsByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("password", "password");
        propertiesMap.put("hasDvd", true);

        List<User> users = dao.findByPropertyEqual(propertiesMap);
        assertEquals(4, users.size());
    }
}
