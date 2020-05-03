package com.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Movies")
@Table(name = "Movies")
@JsonIgnoreProperties({"movieCollections"})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "runtime")
    private int runtime;

    @Column(name = "ratingMPAA")
    private String ratingMPAA;

    @Column(name = "releaseYear")
    private String releaseYear;

    @Column(name = "plot")
    private String plot;

//    TODO: this probably shouldn't be a string
    @Column(name = "genre")
    private String genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MovieCollection> movieCollections = new HashSet<MovieCollection>();

    public Movie() {

    }

    public Movie(String imdbId, String title, String image, int runtime, String ratingMPAA, String releaseYear, String plot, String genre) {
        this.imdbId = imdbId;
        this.title = title;
        this.image = image;
        this.runtime = runtime;
        this.ratingMPAA = ratingMPAA;
        this.releaseYear = releaseYear;
        this.plot = plot;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
        Movie movie = (Movie) o;
        return id == movie.id &&
                runtime == movie.runtime &&
                Objects.equals(imdbId, movie.imdbId) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(image, movie.image) &&
                Objects.equals(ratingMPAA, movie.ratingMPAA) &&
                Objects.equals(releaseYear, movie.releaseYear) &&
                Objects.equals(plot, movie.plot) &&
                Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdbId, title, image, runtime, ratingMPAA, releaseYear, plot, genre);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", runtime=" + runtime +
                ", ratingMPAA='" + ratingMPAA + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", plot='" + plot + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}