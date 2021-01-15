package nl.qnh.qforce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Main Object class of movies and their attributes
 *
 * @author jordi
 */
public class MovieModel implements Movie {

    @JsonProperty("title")
    private String title;
    @JsonProperty("episode_id")
    private Integer episode;
    @JsonProperty("director")
    private String director;
    @JsonProperty("release_date")
    private LocalDate releaseDate;

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
     * method the get the release date of a movie
     *
     * @return the release date of the movie
     */
    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
