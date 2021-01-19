package nl.qnh.qforce.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class made for the list of people returning from the API-call
 * (Looking for alternative implementation, looks unnecessary...)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonModelList {

    @JsonProperty("results")
    private List<PersonModel> results = null;

    /**
     * No use for constructor because of serialization
     */
    public PersonModelList() {}
    
    @JsonProperty("results")
    public List<PersonModel> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<PersonModel> results) {
        this.results = results;
    }
}
