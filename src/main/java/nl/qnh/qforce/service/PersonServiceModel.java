package nl.qnh.qforce.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.QforceApplication;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.MovieModel;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.domain.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



/**
 * The service file is the blueprint of the methods we are providing.
 * In this class the data gets consumed from an external API and parsed into objects.
 *
 * @author Jordi
 */
@Service
public class PersonServiceModel implements PersonService{

    private static final Logger log = LoggerFactory.getLogger(QforceApplication.class);

    private static final String GET_PEOPLE = "https://swapi.dev/api/people/";
    private static final String GET_MOVIES = "https://swapi.dev/api/films/";

    //object mapper is used to map the JSON values into JAVA Object.
    private final ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    @Autowired
    public PersonServiceModel(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

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
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //TODO: Adding header specifics? how?
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(GET_PEOPLE, HttpMethod.GET, entity, String.class);

        List<Person> people = new ArrayList<>();
        try {
            //ERROR: what is wrong here?
            List<PersonModel> results = objectMapper.readValue(response.getBody(), PersonModel.class);

            if(results == null){
                //Return an empty list
                return new ArrayList<>();
            }
            else{
                //Using final keyword because a variable holds a constant value or a reference
                for (final PersonModel person : results) {

                    //get all the characters that contain something in the query
                    if(query != null){
                        String str = query;
                        if (str.trim().length() == 0) {
                            log.warn("string only contains whitespace");
                            break;
                        }
                        else if (person.getName().contains(query)) {
                            log.warn("Found Result: " + person.getName());
                            //TODO: add to list
                            //people.add(person);

                        }
                        else{
                            continue;
                        }
                    }

                }
                return people;
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e.getCause());
            log.error("Error: GetPeople() method ");
            return new ArrayList<>();
        }
    }

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
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //TODO: Adding header specifics? how?
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(GET_PEOPLE + "id", HttpMethod.GET, entity, String.class);

        PersonModel person;

        try {
            person = objectMapper.readValue(response.getBody(), PersonModel.class);

            if (person == null) {
                return Optional.empty();
            }
            else{
                //Add the names of the movies instead of the URL's into the person class
                person.setMovies(loadMovies(person.getMoviesURL()));
                return Optional.of(person);
            }

        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e.getCause());
            log.error("Error: GetPeople() method ");
            return Optional.empty();
        }
    }

    /**
     * method to convert a list of movie urls to movie objects
     * @param movieURLs a list with the SWAPI URL of the different movies
     * @return a list of movies
     */
    private List<Movie> loadMovies(List<String> movieURLs) throws JsonProcessingException {
        List<Movie> movies = new ArrayList<>();

        for (String movieURL : movieURLs) {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            // set `accept` header
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            //TODO: Adding header specifics? how?
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<String> response = restTemplate.exchange(movieURL, HttpMethod.GET, entity, String.class);

            MovieModel movie = objectMapper.readValue(response.getBody(), MovieModel.class);
            movies.add(movie);
        }
        return movies;
    }

}
