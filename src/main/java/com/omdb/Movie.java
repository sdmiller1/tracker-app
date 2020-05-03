package com.omdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie{

	@JsonProperty("Runtime")
	private String runtime;

	@JsonProperty("Language")
	private String language;


// TODO: switch with year
	@JsonProperty("Released")
	private String released;

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

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"runtime='" + runtime + '\'' +
				", language='" + language + '\'' +
				", released='" + released + '\'' +
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