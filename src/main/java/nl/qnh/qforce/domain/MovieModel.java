package nl.qnh.qforce.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Main Object class of movies and their attributes
 *
 * @author jordi
 */
@JsonPropertyOrder({ "title", "episode_id", "director", "release_date"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieModel implements Movie {

    @JsonProperty("title")
    private String title;
    @JsonProperty("episode_id")
    private Integer episode;
    @JsonProperty("director")
    private String director;
    @JsonProperty("release_date")
    private String date;

    private LocalDate releaseDate;

    /**
     * empty constructor for serialization.
     */
    public MovieModel() {}

    /**
     * The constructor for a movie object implemented by the movie interface.
     * 
     * @param title the title of the movie
     * @param episode the episode of the series
     * @param director the director of the movie
     * @param releaseDate the release date if the movie
     */
    public MovieModel(String title, Integer episode, String director, LocalDate releaseDate) {
        this.title = title;
        this.episode = episode;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    /**
     * method the get the title of a movie
     *
     * @return the title of the movie
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * method the get the episode of a series.
     *
     * @return the episode of a movie
     */
    @Override
    public Integer getEpisode() {
        return episode;
    }

    /**
     * method the get the director of a movie
     *
     * @return the director of the movie
     */
    @Override
    public String getDirector() {
        return director;
    }

    /**
     * Convert String date to LocalDate Object
     * 
     * @param date the release date of the movie in string format.
     */
    public void setReleaseDate() {
        this.releaseDate = LocalDate.parse(getDate());
    }

    /**
     * Getter for the date in String value.
     * 
     * @return the release date in String value.
     */
    private String getDate() {
        return date;
    }

    /**
     * method the get the release date of a movie
     *
     * @return the release date of the movie
     */
    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
