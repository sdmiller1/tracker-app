package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Rating.
 */
@Entity(name = "Ratings")
@Table(name = "Ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "dateWatched")
    private String date;

    @Column(name = "rating")
    private int rating;


    /**
     * Instantiates a new Rating.
     */
    public Rating() {

    }

    /**
     * Instantiates a new Rating.
     *
     * @param user   the user
     * @param movie  the movie
     * @param date   the date
     * @param rating the rating
     */
    public Rating(User user, Movie movie, String date, int rating) {
        this.user = user;
        this.movie = movie;
        this.date = date;
        this.rating = rating;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets movie.
     *
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets movie.
     *
     * @param movie the movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return id == rating1.id &&
                rating == rating1.rating &&
                Objects.equals(user, rating1.user) &&
                Objects.equals(movie, rating1.movie) &&
                Objects.equals(date, rating1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, movie, date, rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", movie=" + movie +
                ", date='" + date + '\'' +
                ", rating=" + rating +
                '}';
    }
}
