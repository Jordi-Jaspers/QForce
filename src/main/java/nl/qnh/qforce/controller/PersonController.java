package nl.qnh.qforce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nl.qnh.qforce.QforceApplication;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.service.PersonService;


/**
 * The controller of the person telling what kind of methods are usable of the provided service
 *
 * @author jordi
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;

    private static final Logger log = LoggerFactory.getLogger(QforceApplication.class);

    /**
     * wiring the service bean to the controller in the constructor.
     * 
     * @param personService
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * The mapping to start the get ID service in the API.
     * 
     * @param id the id of the person
     * @return optional person if the id exists
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Person> getPerson(@PathVariable long id){
        log.info("received id: " + id);
        return personService.get(id);
    }

    /**
     * The mapping to start the search service in the API.
     * 
     * @param query search parameter
     * @return list of persons containing the query
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Person> getQuery(@RequestParam(value = "q") String query){
        log.info("received query: " + query);
        return personService.search(query);
    }

}
