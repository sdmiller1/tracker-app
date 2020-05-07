package com.movieapp.api;

import com.movieapp.controller.GenericDao;
import com.movieapp.controller.RatingController;
import com.movieapp.model.Movie;
import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Service that manipulates the ratings
 */
@Path("/ratings")
public class RatingService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private RatingController ratingController = new RatingController();

    /**
     * Add rating for a movie
     *
     * @param imdbId       the imdb id
     * @param ratingString the rating string
     * @param username     the username
     * @return the response
     */
    @POST
    @Produces("text/plain")
    @Path("/id={imdbid}&rating={rating}&user={username}")
    public Response addRatingForMovie(@PathParam("imdbid") String imdbId, @PathParam("rating") String ratingString, @PathParam("username") String username) {

        String result = "Failed to submit the rating";

        try {
            int ratingValue = Integer.parseInt(ratingString);

            result = ratingController.rateMovie(imdbId, username, ratingValue);
        } catch (Exception e) {
            logger.error(e);
        }

        if (result.equals("Successfully Rated")) {
            result = "Successfully rated the movie";
        }

        return Response.status(200).entity(result).build();
    }

}