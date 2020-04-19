package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.MovieCollection;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

/**
 * Used to update the user's collections
 */
public class CollectionUpdater {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
    private GenericDao<MovieCollection> movieCollectionGenericDao = new GenericDao<>(MovieCollection.class);
    private GenericDao<Collection> collectionGenericDao = new GenericDao<>(Collection.class);

    /**
     * Add a movie to a user's collection
     *
     * @param imdbId       the imdb id
     * @param username     the username
     * @param collectionId the Id of the collection
     * @return the id of the entry that was just added or 0 if error
     */
    public int addMovieToUserCollection(String imdbId, String username, int collectionId) {

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        Collection collection = collectionGenericDao.getById(collectionId);

        boolean movieAlreadyInCollection = collection.getMovies().contains(movie);

        int entryId = 0;

        if (collection.getUser().getUsername().equals(username) && !movieAlreadyInCollection) {

            MovieCollection movieCollection = new MovieCollection(collection, movie, false, false, false);
            entryId = movieCollectionGenericDao.insert(movieCollection);

        }

        return entryId;
    }

    /**
     * Remove movie from user collection.
     *
     * @param imdbId       the imdb id
     * @param username     the username
     * @param collectionId the collection id
     */
    public void removeMovieFromUserCollection(String imdbId, String username, int collectionId) {

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        Collection collection = collectionGenericDao.getById(collectionId);

        if (collection.getUser().getUsername().equals(username)) {

            MovieCollection movieCollection = movieCollectionGenericDao.findByPropertyEqual("movie", movie).get(0);
            movieCollectionGenericDao.delete(movieCollection);

        }

    }
}
