package com.movieapp.controller;

import com.movieapp.model.Movie;
import com.movieapp.model.MovieCollection;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CollectionUpdaterTest {

    GenericDao<MovieCollection> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());


    private GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
    private GenericDao<User> userGenericDao = new GenericDao<>(User.class);

    private CollectionUpdater collectionUpdater = new CollectionUpdater();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        dao = new GenericDao<MovieCollection>(MovieCollection.class);
    }

    @Test
    void addMovieToUsersPersonalCollection() {
        String username = "astroscott";
        int collectionId = 4;
        String movieTitle = "The Martian";

        Movie movie = movieGenericDao.findByPropertyEqual("title", movieTitle).get(0);
        User user = userGenericDao.findByPropertyEqual("username", username).get(0);


        int entryId = collectionUpdater.addMovieToUserCollection(movie.getImdbId(), username, collectionId);

        MovieCollection movieCollection = dao.getById(entryId);

        assertEquals(movie, movieCollection.getMovie());
        assertEquals(user, movieCollection.getCollection().getUser());

    }

    @Test
    void addMovieToCollectionAlreadyExists() {
        String username = "astrobob";
        int collectionId = 1;
        String movieTitle = "The Martian";

        Movie movie = movieGenericDao.findByPropertyEqual("title", movieTitle).get(0);
        User user = userGenericDao.findByPropertyEqual("username", username).get(0);


        int entryId = collectionUpdater.addMovieToUserCollection(movie.getImdbId(), username, collectionId);

        assertEquals(-1, entryId);
    }

    @Test
    void removeMovieFromUsersPersonalCollection() {
        String username = "astrobob";
        int collectionId = 1;
        String movieTitle = "The Martian";

        Movie movie = movieGenericDao.findByPropertyEqual("title", movieTitle).get(0);


        String message = collectionUpdater.removeMovieFromUserCollection(movie.getImdbId(), username, collectionId);

        assertEquals("Success", message);
    }

    @Test
    void removeMovieFromUsersPersonalCollectionFailure() {
        String username = "wrong username";
        int collectionId = 1;
        String movieTitle = "The Martian";

        Movie movie = movieGenericDao.findByPropertyEqual("title", movieTitle).get(0);


        String message = collectionUpdater.removeMovieFromUserCollection(movie.getImdbId(), username, collectionId);

        assertEquals("Failed to remove movie", message);
    }
}
