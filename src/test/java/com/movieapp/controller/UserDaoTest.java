package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Tests for the User class
 */
public class UserDaoTest {

    private GenericDao<User> dao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<User>(User.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        User user = dao.getById(1);
        assertEquals(1, user.getId());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        User user = dao.getById(1);
        user.setFirstName("Matt");
        user.setLastName("Damon");
        dao.saveOrUpdate(user);
        assertEquals(user, dao.getById(1));
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User user = new User("Brad", "Pitt", "movieGuy12", "password", true, true, true);
        int id = dao.insert(user);
        assertEquals(user, dao.getById(id));
    }

    /**
     * Insert with rating.
     */
    @Test
    void insertWithRating() {
        User user = new User("Brad", "Pitt", "movieGuy12", "password", true, true, true);
        Movie movie = new GenericDao<Movie>(Movie.class).getById(2);

        Rating rating = new Rating(user, movie, "2020-02-25", 4);

        user.addRating(rating);

        int id = dao.insert(user);
        assertEquals(user, dao.getById(id));
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        User user = dao.getById(1);
        dao.delete(user);
        assertNull(dao.getById(1));

        // verify that ratings are deleted along with the user
        GenericDao<Rating> ratingGenericDao = new GenericDao<>(Rating.class);
        assertNull(ratingGenericDao.getById(1));
        assertNull(ratingGenericDao.getById(5));

        // Verify that collections were deleted
        GenericDao<Collection> collectionGenericDao = new GenericDao<>(Collection.class);
        assertNull(collectionGenericDao.getById(1));
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<User> users = dao.getAll();
        assertEquals(4, users.size());
    }

    /**
     * Gets users by property equals.
     */
    @Test
    void getUsersByPropertyEquals() {
        List<User> users = dao.findByPropertyEqual("password", "password");
        assertEquals(4, users.size());
    }

    /**
     * Gets users by property equals map.
     */
    @Test
    void getUsersByPropertyEqualsMap() {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        propertiesMap.put("password", "password");
        propertiesMap.put("hasDvd", true);

        List<User> users = dao.findByPropertyEqual(propertiesMap);
        assertEquals(4, users.size());
    }

    /**
     * Gets users by property like.
     */
    @Test
    void getUsersByPropertyLike() {
        List<User> users = dao.findByPropertyLike("username", "astro");
        assertEquals(4, users.size());
    }

    /**
     * Gets movies rated by user.
     */
    @Test
    void getMoviesRatedByUser() {
        String username = "astrobob";
        User user = dao.findByPropertyEqual("username", username).get(0);

        List<Movie> movies = user.getRatedMovies();

        assertEquals(1, movies.size());
        assertEquals("The Martian", movies.get(0).getTitle());
    }

    @Test
    void checkIfUserIsAdmin() {
        String username = "astroscott";
        User user = dao.findByPropertyEqual("username", username).get(0);

        boolean isAdmin = user.isAdmin();

        assertTrue(isAdmin);
    }
}
