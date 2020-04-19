package com.movieapp.api;

import com.movieapp.controller.CollectionUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/collections")
public class CollectionSevice {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private CollectionUpdater collectionUpdater = new CollectionUpdater();


//    TODO: replace with post/delete, also pass in bluray/dvd/4k values
    @GET
    @Produces("text/plain")
    @Path("/{param}")
    public Response addMovieToCollection(@PathParam("param") String imdbId) {

//        TODO: This needs to be got from the request not hardcoded
        String username = "astroscott";
        String collectionName = "personal";

        String result = "Failed to add the Movie";

        int newEntryId = collectionUpdater.addMovieToUserCollection(imdbId, username, collectionName);

        if (newEntryId != 0) {
            result = "The Movie was added";
        }

        return Response.status(200).entity(result).build();
    }
}