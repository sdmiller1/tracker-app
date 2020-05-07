package com.movieapp.api;

import com.movieapp.controller.CollectionUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Service that manipulates the collections
 */
@Path("/collections")
public class CollectionService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private CollectionUpdater collectionUpdater = new CollectionUpdater();


    /**
     * Add movie to collection
     *
     * @param imdbId       the imdb id
     * @param collectionId the collection id
     * @param username     the username
     * @return the response
     */
//    TODO: pass in bluray/dvd/4k values
    @POST
    @Produces("text/plain")
    @Path("/id={imdbid}&collection={collectionId}&user={username}")
    public Response addMovieToCollection(@PathParam("imdbid") String imdbId, @PathParam("collectionId") String collectionId, @PathParam("username") String username) {

        String result = "Failed to add the Movie";

        int newEntryId = 0;

        try {
            newEntryId = collectionUpdater.addMovieToUserCollection(imdbId, username, Integer.parseInt(collectionId));
        } catch (Exception e) {
            logger.error(e);
        }

        if (newEntryId > 0) {
            result = "The Movie was added";
        } else if (newEntryId == -1) {
            result = "The Movie was already in the collection";
        }

        return Response.status(200).entity(result).build();
    }

    /**
     * Remove movie from collection
     *
     * @param imdbId       the imdb id
     * @param collectionId the collection id
     * @param username     the username
     * @return response
     */
    @DELETE
    @Produces("text/plain")
    @Path("/id={imdbid}&collection={collectionId}&user={username}")
    public Response removeMovieFromCollection(@PathParam("imdbid") String imdbId, @PathParam("collectionId") String collectionId, @PathParam("username") String username) {

        String result;

        String message = "Failed to remove the movie";

        try {
            message = collectionUpdater.removeMovieFromUserCollection(imdbId, username, Integer.parseInt(collectionId));
        } catch (Exception e) {
            logger.error(e);
        }

        if (message.equals("Success")) {
            result = "Successfully removed the Movie";
        } else {
            result = message;
        }

        return Response.status(200).entity(result).build();
    }
}