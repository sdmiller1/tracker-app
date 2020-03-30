package com.movieapp.controller;

import com.movieapp.model.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MovieSearcher {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<Movie> dao = new GenericDao<>(Movie.class);

    private OMDBDao apiDao = new OMDBDao();

    public List<Movie> findByTitle(String title) {
        List<Movie> searchResults = dao.findByPropertyEqual("title", title);

        if (searchResults.size() == 0) {
//            movie isnt in the database yet... so call api and add it to db
        }

        return searchResults;
    }

}
