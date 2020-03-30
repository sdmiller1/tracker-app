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
        List<Movie> searchResults;
        searchResults = dao.findByPropertyLike("title", title);

        if (searchResults.size() == 0) {
            int id = apiDao.addMovieToDatabase(title);
            searchResults = dao.findByPropertyEqual("id", id);
        }

        return searchResults;
    }

    public Movie findById(String id) {

        List<Movie> movies = dao.findByPropertyEqual("imdbId", id);

        Movie movie = movies.get(0);

        return movie;
    }

}
