package com.movieapp.model;

import com.movieapp.controller.GenericDao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Users")
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "hasDvd")
    private boolean hasDvd;

    @Column(name = "hasBluRay")
    private boolean hasBluRay;

    @Column(name = "has4k")
    private boolean has4k;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Rating> ratings = new HashSet<Rating>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Collection> collections = new HashSet<Collection>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<Role>();

    /**
     * empty constructor
     */
    public User() {

    }

    public User(String firstName, String lastName, String username, String password, boolean hasDvd, boolean hasBluRay, boolean has4k) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.hasDvd = hasDvd;
        this.hasBluRay = hasBluRay;
        this.has4k = has4k;
    }

    public List<Movie> getRatedMovies() {
        List<Movie> movies = new ArrayList<>();

        for (Rating rating: this.ratings) {
            movies.add(rating.getMovie());
        }

        return movies;
    }

    public int getMovieRating(String imdbId) {
        GenericDao<Movie> movieGenericDao = new GenericDao<>(Movie.class);
        Movie movie = movieGenericDao.findByPropertyEqual("imdbId", imdbId).get(0);

        int ratingValue = 0;

        for (Rating rating: ratings) {
            if (rating.getMovie().equals(movie)) {
                ratingValue = rating.getRating();
            }
        }

        return ratingValue;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public void removeRating(Rating rating) {
        ratings.remove(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasDvd() {
        return hasDvd;
    }

    public void setHasDvd(boolean hasDvd) {
        this.hasDvd = hasDvd;
    }

    public boolean isHasBluRay() {
        return hasBluRay;
    }

    public void setHasBluRay(boolean hasBluRay) {
        this.hasBluRay = hasBluRay;
    }

    public boolean isHas4k() {
        return has4k;
    }

    public void setHas4k(boolean has4k) {
        this.has4k = has4k;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                hasDvd == user.hasDvd &&
                hasBluRay == user.hasBluRay &&
                has4k == user.has4k &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, password, hasDvd, hasBluRay, has4k);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hasDvd=" + hasDvd +
                ", hasBluRay=" + hasBluRay +
                ", has4k=" + has4k +
                '}';
    }
}