package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Collections")
@Table(name = "Collections")
public class Collection {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "collectionType")
    private String collectionType;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "UsersCollections",
            joinColumns = { @JoinColumn(name = "collection_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "collections")
    private Set<Movie> movies = new HashSet<Movie>();


    public Collection() {

    }

    public Collection(String collectionType, Set<User> users, Set<Movie> movies) {
        this.collectionType = collectionType;
        this.users = users;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return id == that.id &&
                Objects.equals(collectionType, that.collectionType) &&
                Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collectionType, movies);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", collectionType='" + collectionType + '\'' +
                ", movies=" + movies +
                '}';
    }
}
