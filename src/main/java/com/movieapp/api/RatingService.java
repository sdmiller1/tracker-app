package com.movieapp.api;

import com.movieapp.controller.GenericDao;
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

    GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
    GenericDao<Rating> ratingGenericDao = new GenericDao<>(Rating.class);
    GenericDao<User> userGenericDao = new GenericDao<>(User.class);

    @POST
    @Produces("text/plain")
    @Path("/id={imdbid}&rating={rating}&user={username}")
    public Response addRatingForMovie(@PathParam("imdbid") String imdbId, @PathParam("rating") String ratingString, @PathParam("username") String username) {

        String result = "Failed submit the rating";

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        User user = userGenericDao.findByPropertyEqual("username", username).get(0);

        int ratingValue = Integer.parseInt(ratingString);

        Rating rating = new Rating(user, movie, "2020-02-25", ratingValue);

//        TODO: make this save or update but make sure to verify it is inserted/updated also move this code to another class
        int entryId = ratingGenericDao.insert(rating);

        if (entryId != 0) {
            result = "Successfully rated the movie";
        }

        return Response.status(200).entity(result).build();
    }

}