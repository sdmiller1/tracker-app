package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

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

    //TODO change to a date object maybe?
    @Column(name = "dateWatched")
    private String date;

    @Column(name = "rating")
    private int rating;



    public Rating() {

    }

    public Rating(User user, Movie movie, String date, int rating) {
        this.user = user;
        this.movie = movie;
        this.date = date;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

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
