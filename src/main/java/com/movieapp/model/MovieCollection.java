package com.movieapp.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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


    public MovieCollection() {
    }

    public MovieCollection(Collection collection, Movie movie, boolean hasDvd, boolean hasBluRay, boolean has4k) {
        this.collection = collection;
        this.movie = movie;
        this.hasDvd = hasDvd;
        this.hasBluRay = hasBluRay;
        this.has4k = has4k;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
