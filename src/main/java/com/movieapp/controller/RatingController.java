package com.movieapp.controller;

import com.movieapp.model.Movie;
import com.movieapp.model.Rating;
import com.movieapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RatingController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
    private GenericDao<Rating> ratingGenericDao = new GenericDao<>(Rating.class);
    private GenericDao<User> userGenericDao = new GenericDao<>(User.class);

    public String rateMovie(String imdbId, String username, int ratingValue) {

        String result = "Error";

        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);
        User user = userGenericDao.findByPropertyEqual("username", username).get(0);


        Rating rating;

        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("movie", movie);
        propertiesMap.put("user", user);

        if (ratingGenericDao.findByPropertyEqual(propertiesMap).size() == 1) {
            rating = ratingGenericDao.findByPropertyEqual(propertiesMap).get(0);
            rating.setRating(ratingValue);
        } else {
            rating = new Rating(user, movie, "2020-01-01", ratingValue);
        }

        ratingGenericDao.saveOrUpdate(rating);

        Rating insertedRating = ratingGenericDao.findByPropertyEqual(propertiesMap).get(0);

        if (rating.equals(insertedRating)) {
            result = "Successfully Rated";
        }

        return result;
    }
}
