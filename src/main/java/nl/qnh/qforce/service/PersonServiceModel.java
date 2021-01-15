package nl.qnh.qforce.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.QforceApplication;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.domain.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceModel implements PersonService{

    private static final Logger log = LoggerFactory.getLogger(QforceApplication.class);

    private static final String GET_PEOPLE = "https://swapi.dev/api/people/";
    private static final String GET_PERSON   = "https://swapi.dev/api/people/{id}";
    private static final String GET_QUERY = "https://swapi.dev/api/people/?search={value}";

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
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(GET_QUERY, HttpMethod.GET, entity, String.class);

        List<Person> people = new ArrayList<>();

        //TODO: get and parse the data into a list
        //TODO: Log any exceptions....

        return people;
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
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(GET_QUERY, HttpMethod.GET, entity, String.class);

        PersonModel person;

        if (person == null) {
            return Optional.empty();
        }

        //TODO: get and parse the data into a list


    }

}
