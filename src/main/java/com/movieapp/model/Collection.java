package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Collections")
@Table(name = "Collections")
public class Collection {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "collectionName")
    private String collectionName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MovieCollection> movieCollections = new HashSet<MovieCollection>();

    public Collection() {

    }

    public Collection(String collectionName, User user) {
        this.collectionName = collectionName;
        this.user = user;
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        for (MovieCollection movieCollection : movieCollections) {
            movies.add(movieCollection.getMovie());
        }

        return movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MovieCollection> getMovieCollections() {
        return movieCollections;
    }

    public void setMovieCollections(Set<MovieCollection> movieCollections) {
        this.movieCollections = movieCollections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return id == that.id &&
                Objects.equals(collectionName, that.collectionName) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collectionName, user);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", collectionName='" + collectionName + '\'' +
                ", user=" + user +
                '}';
    }
}
