package nl.qnh.qforce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.MovieModel;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.domain.PersonModel;
import nl.qnh.qforce.domain.PersonModelList;

/**
 * The service file is the blueprint of the methods we are providing. In this
 * class the data gets consumed from an external API and parsed into objects.
 *
 * @author Jordi
 */
@Service
public class PersonServiceModel implements PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonServiceModel.class);

    private static final String GET_PEOPLE = "https://swapi.dev/api/people/";

    // object mapper is used to map the JSON values into JAVA Object.
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    /**
     * Wire the beans of restTemplate for the body and the JSON object mapper to this model in the constructor.
     * 
     * @param restTemplate restTemplate of the service
     * @param objectMapper Jackson Object mapper.
     */
    @Autowired
    public PersonServiceModel(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Start the method to return a search request with query.
     */
    @Override
    public List<Person> search(String query) {
        return getPeople(query);
    }

    /**
     * Method made to return a list of people with a specified search query.
     *
     * @param query the Query to search for in the list
     * @return list of people with the query
     */
    private List<Person> getPeople(String query) {
        log.info("Getting people response");
        ResponseEntity<String> response = restTemplate.exchange(GET_PEOPLE, HttpMethod.GET, getHttpEntity(), String.class);
        log.info("response code: " + response.getStatusCode());

        if(response.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
            getRedirect(response);
        }

        List<Person> people = new ArrayList<>();

        try {
            PersonModelList results = objectMapper.readValue(response.getBody(), PersonModelList.class);
            log.info("results: " + results.getResults());

            if (response.getBody() == null) {
                log.info("Results empty: Returning empty list");
                return new ArrayList<>();
            } else {
                // Using final keyword because a variable holds a constant value or a reference
                log.info("itterating through the results");
                for (final PersonModel person : results.getResults()) {

                    // get all the characters that contain something in the query
                    if (query != null) {
                        if (query.trim().length() == 0) {
                            log.info("string only contains whitespace");
                            break;
                        } else if (person.getName().regionMatches(true, 0, query, 0, query.length())) {
                            log.info("Found Result: " + person.getName());

                            //getting the id by splitting the url and taking the last element
                            String str[] = person.getPersonalUrl().split("/");
                            person.setId(Long.parseLong(str[str.length-1]));

                            //setting the movies into the person
                            person.setMovies(loadMovies(person.getMoviesURL()));
                            people.add(person);
                        }
                        else{
                            continue;
                        }
                    }

                }
                log.info("people: " + people);
                return people;
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e.getCause());
            log.error("Error: GetPeople() method ");
            return new ArrayList<>();
        }
    }

    /**
     * Start the method to return a specified person with id.
     */
    @Override
    public Optional<Person> get(long id) {
        return getPerson(id);
    }

    /**
     * Method made to return a specified person with id.
     *
     * @param id the id of the person
     * @return star wars character with id
     */
    private Optional<Person> getPerson(long id) {
        log.info("Getting person response");
        ResponseEntity<String> response = restTemplate.exchange(GET_PEOPLE + id, HttpMethod.GET, getHttpEntity(), String.class);
        log.info("response code: " +  response.getStatusCode());
        
        if(response.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
            getRedirect(response);
        }
        
        PersonModel person;

        try {
            person = objectMapper.readValue(response.getBody(), PersonModel.class);

            if (person == null) {
                log.info("No person found: Object empty");
                return Optional.empty();
            } else {
                log.info("Person found: Correct id -> " + id);
                person.setId(id);

                log.info("Adding movies from the URLs into the person object");
                person.setMovies(loadMovies(person.getMoviesURL()));

                return Optional.of(person);
            }

        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e.getCause());
            log.error("Error: GetPersons() method ");
            return Optional.empty();
        }
    }

    /**
     * method to convert a list of movie urls to movie objects
     * 
     * @param movieURLs a list with the SWAPI URL of the different movies
     * @return a list of movies
     */
    private List<Movie> loadMovies(List<String> movieURLs) {
        List<Movie> movies = new ArrayList<>();

        for (String movieURL : movieURLs) {
            log.info("Getting movie response: " + movieURL);
            ResponseEntity<String> response = restTemplate.exchange(movieURL, HttpMethod.GET, getHttpEntity(), String.class);
            log.info("response code: " + response.getStatusCode());
            
            if(response.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
                getRedirect(response);
            }
            
            try {
                if(response.getBody() == null){
                    log.info("No movie found: Object empty");
                    return new ArrayList<>();
                }
                else{
                    MovieModel movie = objectMapper.readValue(response.getBody(), MovieModel.class);
                    log.info("Movie found: Adding " + movie.getTitle() + " To the list");
                    movies.add(movie);
                }
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e.getCause());
                log.error("Error: LoadMovies() method ");
                return new ArrayList<>();
            }
        }
        return movies;
    }
    
    /**
     * Method to create an correct API entity for the GET-calls.
     * 
     * @return the correct entity to make API calls
     */
    private HttpEntity<String> getHttpEntity(){
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //Adding header specifics maybe?
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        log.info("Creating HTTP request entity");
        return entity;
    }

    private ResponseEntity<String> getRedirect(ResponseEntity<String> response){
        String redirect = response.getHeaders().getLocation().toString();
        log.info("redirecting to : " + redirect);
        return restTemplate.exchange(redirect, HttpMethod.GET, getHttpEntity(), String.class);
    }
}
