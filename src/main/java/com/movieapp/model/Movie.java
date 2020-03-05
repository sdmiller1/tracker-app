package com.movieapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Movies")
@Table(name = "Movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "api_id")
    private int apiId;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MovieCollection> movieCollections = new HashSet<MovieCollection>();

    public Movie() {

    }

    public Movie(int apiId, String title) {
        this.apiId = apiId;
        this.title = title;
        // TODO should this be in this constructor? probably not
        //this.movieCollections = movieCollections;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Movie movies = (Movie) o;
        return id == movies.id &&
                apiId == movies.apiId &&
                Objects.equals(title, movies.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apiId, title);
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", apiId=" + apiId +
                ", title='" + title + '\'' +
                '}';
    }
}