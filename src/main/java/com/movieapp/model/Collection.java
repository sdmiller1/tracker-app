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

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MovieCollection> movieCollections = new HashSet<MovieCollection>();

    public Collection() {

    }

    public Collection(String collectionType, Set<User> users) {
        this.collectionType = collectionType;
        this.users = users;
        // TODO should this be in this constructor? probably not
        //this.movieCollections = movieCollections;
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
                Objects.equals(collectionType, that.collectionType) &&
                Objects.equals(movieCollections, that.movieCollections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collectionType, movieCollections);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", collectionType='" + collectionType + '\'' +
                ", movieCollections=" + movieCollections +
                '}';
    }
}
