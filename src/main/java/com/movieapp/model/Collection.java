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

    @Column(name = "ownedByUser")
    private String ownedByUser;

    @Column(name = "collectionName")
    private String collectionName;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "UsersCollections",
            joinColumns = { @JoinColumn(name = "collection_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MovieCollection> movieCollections = new HashSet<MovieCollection>();

    public Collection() {

    }

    public Collection(String collectionName, Set<User> users) {
        this.collectionName = collectionName;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnedByUser() {
        return ownedByUser;
    }

    public void setOwnedByUser(String ownedByUser) {
        this.ownedByUser = ownedByUser;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
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
                Objects.equals(ownedByUser, that.ownedByUser) &&
                Objects.equals(collectionName, that.collectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownedByUser, collectionName);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", ownedByUser='" + ownedByUser + '\'' +
                ", collectionName='" + collectionName + '\'' +
                '}';
    }
}
