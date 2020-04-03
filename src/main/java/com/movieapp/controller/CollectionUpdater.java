package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.MovieCollection;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class CollectionUpdater {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
    private GenericDao<User> userGenericDao = new GenericDao<>(User.class);
    private GenericDao<MovieCollection> movieCollectionGenericDao = new GenericDao<>(MovieCollection.class);

    /**
     * Add a movie to a user's collection
     *
     * @param imdbId         the imdb id
     * @param username       the username
     * @param collectionName the collection name
     * @return the id of the entry that was just added or 0 if error
     */
    public int addMovieToUserCollection(String imdbId, String username, String collectionName) {

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        User user = userGenericDao.findByPropertyEqual("username", username).get(0);

        Set<Collection> collections = user.getCollections();

        int entryId = 0;

        for (Collection collection: collections) {
            if (collection.getCollectionName().equals(collectionName)) {

                MovieCollection movieCollection = new MovieCollection(collection, movie, true, false, false);
                entryId = movieCollectionGenericDao.insert(movieCollection);

            }
        }

        return entryId;
    }

    public void removeMovieFromUserCollection(String imdbId, String username, String collectionName) {

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        User user = userGenericDao.findByPropertyEqual("username", username).get(0);

        Set<Collection> collections = user.getCollections();

        for (Collection collection: collections) {
            if (collection.getCollectionName().equals(collectionName)) {

                MovieCollection movieCollection = movieCollectionGenericDao.findByPropertyEqual("movie", movie).get(0);
                movieCollectionGenericDao.delete(movieCollection);

            }
        }

    }
}
