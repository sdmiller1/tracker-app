package com.movieapp.controller;

import com.movieapp.model.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OMDBDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private OMDBDao dao = new OMDBDao();
    private GenericDao<Movie> genericDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

        genericDao = new GenericDao<Movie>(Movie.class);
    }

    @Test
    public void testAPIForResponse() {
        com.omdb.Movie movie = dao.getMovieByTitle("The Martian");

        assertEquals("The Martian", movie.getTitle());
    }

    @Test
    public void addMovieToDBFromAPI() {
        String title = "Interstellar";

        dao.addMovieToDatabase(title);

        List<Movie> movies = genericDao.findByPropertyEqual("title", title);

        assertEquals(movies.size(), 1);
    }
}