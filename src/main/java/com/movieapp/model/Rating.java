package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity(name = "Ratings")
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "movie")
    private String movie;

    @ManyToOne
    private User user;



    public Rating() {
    }

    public Rating(String movie, int rating, User user) {
        this.rating = rating;
        this.user = user;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", user=" + user +
                '}';
    }
}
