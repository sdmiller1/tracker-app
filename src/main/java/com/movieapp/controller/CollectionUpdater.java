package com.movieapp.controller;

import com.movieapp.model.Collection;
import com.movieapp.model.Movie;
import com.movieapp.model.MovieCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * Used to update the user's movie collections
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
     * @return the id of the entry that was just added or 0 if error -1 if already exists
     */
    public int addMovieToUserCollection(String imdbId, String username, int collectionId) {

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        Collection collection = collectionGenericDao.getById(collectionId);

        boolean movieAlreadyInCollection = collection.getMovies().contains(movie);

        int entryId = 0;

        if (collection.getUser().getUsername().equals(username) && !movieAlreadyInCollection) {

            MovieCollection movieCollection = new MovieCollection(collection, movie, false, false, false);
            entryId = movieCollectionGenericDao.insert(movieCollection);

        } else if (collection.getUser().getUsername().equals(username) && movieAlreadyInCollection) {
            entryId = -1;
        }

        return entryId;
    }

    /**
     * Remove movie from user collection.
     *
     * @param imdbId       the imdb id
     * @param username     the username
     * @param collectionId the collection id
     * @return success or failure message
     */
    public String removeMovieFromUserCollection(String imdbId, String username, int collectionId) {

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        Collection collection = collectionGenericDao.getById(collectionId);

        if (collection.getUser().getUsername().equals(username)) {
            Map<String, Object> propertiesMap = new HashMap<>();
            propertiesMap.put("movie", movie);
            propertiesMap.put("collection", collection);

            MovieCollection movieCollection = movieCollectionGenericDao.findByPropertyEqual(propertiesMap).get(0);
            int movieCollectionId = movieCollection.getId();

            movieCollectionGenericDao.delete(movieCollection);

            if (movieCollectionGenericDao.getById(movieCollectionId) == null) {
                return "Success";
            }
        }

        return "Failed to remove movie";

    }
}
