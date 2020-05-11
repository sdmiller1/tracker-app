package com.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Movie.
 */
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

//    TODO: Future version this should be a separate table in the database
    @Column(name = "genre")
    private String genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MovieCollection> movieCollections = new HashSet<MovieCollection>();

    /**
     * Instantiates a new Movie.
     */
    public Movie() {

    }

    /**
     * Instantiates a new Movie.
     *
     * @param imdbId      the imdb id
     * @param title       the title
     * @param image       the image
     * @param runtime     the runtime
     * @param ratingMPAA  the rating mpaa
     * @param releaseYear the release year
     * @param plot        the plot
     * @param genre       the genre
     */
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
     * Gets imdb id.
     *
     * @return the imdb id
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Sets imdb id.
     *
     * @param imdbId the imdb id
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets runtime.
     *
     * @return the runtime
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * Sets runtime.
     *
     * @param runtime the runtime
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * Gets rating mpaa.
     *
     * @return the rating mpaa
     */
    public String getRatingMPAA() {
        return ratingMPAA;
    }

    /**
     * Sets rating mpaa.
     *
     * @param ratingMPAA the rating mpaa
     */
    public void setRatingMPAA(String ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    /**
     * Gets release year.
     *
     * @return the release year
     */
    public String getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear the release year
     */
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets plot.
     *
     * @return the plot
     */
    public String getPlot() {
        return plot;
    }

    /**
     * Sets plot.
     *
     * @param plot the plot
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets movie collections.
     *
     * @return the movie collections
     */
    public Set<MovieCollection> getMovieCollections() {
        return movieCollections;
    }

    /**
     * Sets movie collections.
     *
     * @param movieCollections the movie collections
     */
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