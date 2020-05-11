package com.movieapp.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Movie collection.
 */
@Entity(name = "MoviesCollections")
@Table(name = "MoviesCollections")
public class MovieCollection {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private Collection collection;

    @ManyToOne
    private Movie movie;

    @Column(name = "hasDvd")
    private boolean hasDvd;

    @Column(name = "hasBluRay")
    private boolean hasBluRay;

    @Column(name = "has4k")
    private boolean has4k;


    /**
     * Instantiates a new Movie collection.
     */
    public MovieCollection() {
    }

    /**
     * Instantiates a new Movie collection.
     *
     * @param collection the collection
     * @param movie      the movie
     * @param hasDvd     the has dvd
     * @param hasBluRay  the has blu ray
     * @param has4k      the has 4 k
     */
    public MovieCollection(Collection collection, Movie movie, boolean hasDvd, boolean hasBluRay, boolean has4k) {
        this.collection = collection;
        this.movie = movie;
        this.hasDvd = hasDvd;
        this.hasBluRay = hasBluRay;
        this.has4k = has4k;
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
     * Gets collection.
     *
     * @return the collection
     */
    public Collection getCollection() {
        return collection;
    }

    /**
     * Sets collection.
     *
     * @param collection the collection
     */
    public void setCollection(Collection collection) {
        this.collection = collection;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCollection that = (MovieCollection) o;
        return id == that.id &&
                hasDvd == that.hasDvd &&
                hasBluRay == that.hasBluRay &&
                has4k == that.has4k &&
                Objects.equals(collection, that.collection) &&
                Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collection, movie, hasDvd, hasBluRay, has4k);
    }

    @Override
    public String toString() {
        return "MovieCollection{" +
                "id=" + id +
                ", collection=" + collection +
                ", movie=" + movie +
                ", hasDvd=" + hasDvd +
                ", hasBluRay=" + hasBluRay +
                ", has4k=" + has4k +
                '}';
    }
}
