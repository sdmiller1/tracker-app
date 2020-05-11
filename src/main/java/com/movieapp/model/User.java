package com.movieapp.model;

import com.movieapp.controller.GenericDao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * The type User.
 */
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

    /**
     * Instantiates a new User.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param username  the username
     * @param password  the password
     * @param hasDvd    the has dvd
     * @param hasBluRay the has blu ray
     * @param has4k     the has 4 k
     */
    public User(String firstName, String lastName, String username, String password, boolean hasDvd, boolean hasBluRay, boolean has4k) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.hasDvd = hasDvd;
        this.hasBluRay = hasBluRay;
        this.has4k = has4k;
    }

    /**
     * Gets rated movies.
     *
     * @return the rated movies
     */
    public List<Movie> getRatedMovies() {
        List<Movie> movies = new ArrayList<>();

        for (Rating rating: this.ratings) {
            movies.add(rating.getMovie());
        }

        return movies;
    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        for (Role role: roles) {
            if (role.getRoleName().equals("admin")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets movie rating.
     *
     * @param imdbId the imdb id
     * @return the movie rating
     */
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

    /**
     * Add rating.
     *
     * @param rating the rating
     */
    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    /**
     * Remove rating.
     *
     * @param rating the rating
     */
    public void removeRating(Rating rating) {
        ratings.remove(rating);
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Is has dvd boolean.
     *
     * @return the boolean
     */
    public boolean isHasDvd() {
        return hasDvd;
    }

    /**
     * Sets has dvd.
     *
     * @param hasDvd the has dvd
     */
    public void setHasDvd(boolean hasDvd) {
        this.hasDvd = hasDvd;
    }

    /**
     * Is has blu ray boolean.
     *
     * @return the boolean
     */
    public boolean isHasBluRay() {
        return hasBluRay;
    }

    /**
     * Sets has blu ray.
     *
     * @param hasBluRay the has blu ray
     */
    public void setHasBluRay(boolean hasBluRay) {
        this.hasBluRay = hasBluRay;
    }

    /**
     * Is has 4 k boolean.
     *
     * @return the boolean
     */
    public boolean isHas4k() {
        return has4k;
    }

    /**
     * Sets has 4 k.
     *
     * @param has4k the has 4 k
     */
    public void setHas4k(boolean has4k) {
        this.has4k = has4k;
    }

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
    public Set<Rating> getRatings() {
        return ratings;
    }

    /**
     * Sets ratings.
     *
     * @param ratings the ratings
     */
    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    /**
     * Gets collections.
     *
     * @return the collections
     */
    public Set<Collection> getCollections() {
        return collections;
    }

    /**
     * Sets collections.
     *
     * @param collections the collections
     */
    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
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