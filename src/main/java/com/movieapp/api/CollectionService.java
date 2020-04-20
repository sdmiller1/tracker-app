package com.movieapp.api;

import com.movieapp.controller.CollectionUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
//    TODO: replace with post/delete, also pass in bluray/dvd/4k values
    @GET
    @Produces("text/plain")
    @Path("/id={imdbid}&collection={collectionId}&user={username}")
    public Response addMovieToCollection(@PathParam("imdbid") String imdbId, @PathParam("collectionId") String collectionId, @PathParam("username") String username) {

        String result = "Failed to add the Movie";

        int newEntryId = collectionUpdater.addMovieToUserCollection(imdbId, username, Integer.parseInt(collectionId));

        if (newEntryId != 0) {
            result = "The Movie was added";
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(result).build();
    }
}