package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.MovieCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Contains methods for finding movies based off certain criteria
 */
public class MovieSearcher {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
    private GenericDao<Collection> collectionGenericDao = new GenericDao<>(Collection.class);

    private OMDBDao apiDao = new OMDBDao();

    /**
     * Find by title
     *
     * @param title the title
     * @return the list of movies
     */
    public List<Movie> findByTitle(String title) {
        List<Movie> searchResults;
        searchResults = movieGenericDao.findByPropertyLike("title", title);

        if (searchResults.isEmpty()) {
            int id = apiDao.addMovieToDatabase(title);
            searchResults = movieGenericDao.findByPropertyEqual("id", id);
        }

        return searchResults;
    }

    /**
     * Find movie by id
     *
     * @param id the id
     * @return the movie
     */
    public Movie findById(String id) {

        List<Movie> movies = movieGenericDao.findByPropertyEqual("imdbId", id);

        Movie movie = null;

        if (!movies.isEmpty()) {
            movie = movies.get(0);
        }

        return movie;
    }

    /**
     * Find by collection id
     *
     * @param collectionId the collection id
     * @return a list of movies
     */
    public List<Movie> findByCollectionId(int collectionId) {

        List<Movie> movies = new ArrayList<>();

        Collection collection = collectionGenericDao.getById(collectionId);

        Set<MovieCollection> movieCollections = collection.getMovieCollections();

        for (MovieCollection movieCollection : movieCollections) {
            movies.add(movieCollection.getMovie());
        }

        return movies;
    }

}
