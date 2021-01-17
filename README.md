# QForce

The QForce REST API returns Star Wars characters.
It uses the https://swapi.co/ to retrieve the actual Star Wars characters.

Website changed from https://swapi.co/ to https://swapi.dev/

## Objective

Create a REST endpoint to search Star Wars characters by name or retrieve them by id.

The endpoint should look like:

* Search: `/persons?q=r2`
* Get: `/persons/{id}`

The search endpoint should return a list of `Person` resources.

The `Person` resource should look like:

```
{
  "id": 1,
  "name": "Luke Skywalker",
  "birth_year": "19BBY",
  "gender": "MALE",
  "height": 172,
  "weight": 77,
  "movies": [
    {
      "title": "The Empire Strikes Back",
      "episode": 5,
      "director": "Irvin Kershner",
      "release_date": "1980-05-17"
    },
    ..
  ]
}

```

The get endpoint should return a single `Person` resource and if the id does not exists it should return a `404`.

The api should write analytics to a (embedded) database to keep track how many times the api is called.


## Requirements / Objectives

* Fork this project.
* Setup a decent build environment (Maven or Gradle).
* Use Spring framework and Spring MVC in particular. Spring Boot recommended.
* The project should use the `nl.qnh.qforce.domain` and `nl.qnh.qforce.service` interfaces. Implementations must be developed and these interfaces may not be changed.
* The https://swapi.co/ must be used to retrieve the external people data.
* Jackson object mapper (https://github.com/FasterXML/jackson) should be used for marshalling and unmarshalling JSON.
* The QForce api should return the JSON data in snake case format.
* Unit and integration tests should be written.
* An embedded database (e.g. H2) should be used for storing the analytics.
* Implementation decisions need to be documented in the Javadocs. 

