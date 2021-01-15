package nl.qnh.qforce.controller;
import nl.qnh.qforce.QforceApplication;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * The controller of the person telling what kind of methods are usable of the provided service
 *
 * @author jordi
 */
@RestController
@RequestMapping("/api")
public class PersonController {

    private PersonService personService;

    private static final Logger log = LoggerFactory.getLogger(QforceApplication.class);

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<Person> getPeople(){
        log.debug("received list of all people");
        return null;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Optional<Person> getPerson(@PathVariable("id") long id){
        log.debug("received id: " + id);
        return personService.get(id);
    }

    @RequestMapping(value = "/people/search={query}", method = RequestMethod.GET)
    public List<Person> getQuery(@PathVariable("query") String query){
        log.debug("received query: " + query);
        return personService.search(query);
    }

}
