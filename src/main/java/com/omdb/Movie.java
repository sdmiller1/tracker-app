package com.omdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The type Movie based on omdb data.
 */
@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie{

	@JsonProperty("Runtime")
	private String runtime;

	@JsonProperty("Language")
	private String language;

	@JsonProperty("imdbID")
	private String imdbID;

	@JsonProperty("Plot")
	private String plot;

	@JsonProperty("Title")
	private String title;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("Genre")
	private String genre;

	@JsonProperty("Rated")
	private String rated;

	/**
	 * Gets rated.
	 *
	 * @return the rated
	 */
	public String getRated() {
		return rated;
	}

	/**
	 * Sets rated.
	 *
	 * @param rated the rated
	 */
	public void setRated(String rated) {
		this.rated = rated;
	}

	/**
	 * Gets runtime.
	 *
	 * @return the runtime
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * Sets runtime.
	 *
	 * @param runtime the runtime
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	/**
	 * Gets language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets language.
	 *
	 * @param language the language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets imdb id.
	 *
	 * @return the imdb id
	 */
	public String getImdbID() {
		return imdbID;
	}

	/**
	 * Sets imdb id.
	 *
	 * @param imdbID the imdb id
	 */
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
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
	 * Gets type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets type.
	 *
	 * @param type the type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets year.
	 *
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets year.
	 *
	 * @param year the year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Gets poster.
	 *
	 * @return the poster
	 */
	public String getPoster() {
		return poster;
	}

	/**
	 * Sets poster.
	 *
	 * @param poster the poster
	 */
	public void setPoster(String poster) {
		this.poster = poster;
	}

	/**
	 * Gets country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets country.
	 *
	 * @param country the country
	 */
	public void setCountry(String country) {
		this.country = country;
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

	@Override
	public String toString() {
		return "Movie{" +
				"runtime='" + runtime + '\'' +
				", language='" + language + '\'' +
				", imdbID='" + imdbID + '\'' +
				", plot='" + plot + '\'' +
				", title='" + title + '\'' +
				", type='" + type + '\'' +
				", year='" + year + '\'' +
				", poster='" + poster + '\'' +
				", country='" + country + '\'' +
				", genre='" + genre + '\'' +
				", rated='" + rated + '\'' +
				'}';
	}
}