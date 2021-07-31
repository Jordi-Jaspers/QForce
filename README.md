# QForce (Spring Boot)
## Biography  

**Authors:**  
Jordi Jaspers [[Github](https://github.com/Jordi-Jaspers "Github Page"), [Linkedin](https://www.linkedin.com/in/jordi-jaspers/ "Linkedin Page")]  
  
**Date of initial commit:**  
01/02/2021

**Description:**  
A spring boot backend project where we create an API with REST endpoint to search Star Wars characters by name or retrieve them by id. The Idea is that the endpoint should look like:

* Search: `/persons?q=r2`
* Get: `/persons/{id}`

The search endpoint should return a list of `Person` resources. The `Person` resource should look like the following response.

```JAVA
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

The get endpoint should return a single `Person` resource and if the id does not exists it should return a `404`. The api should write analytics to a (embedded) database to keep track how many times the api is called.

The QForce REST API returns Star Wars characters.
It uses the [QForce](https://swapi.co/, "SWAPI page") to retrieve the actual Star Wars characters.

  
### What We Learned
* Setup a decent build environment Gradle
* Use Spring framework and Spring MVC
* Retrieving data from an external API
* Jackson object mapper (<https://github.com/FasterXML/jackson>) should be used for marshalling and unmarshalling JSON.
* Some Java Spring boot experience
* Unit and integration tests in GROOVY language
* Writing documentation (important)

### References

* Troubleshooting: <https://stackoverflow.com/>
* Jackson object mapper: <https://github.com/FasterXML/jackson>
* QForce API: <https://swapi.co/>
* Spring troubleshooting: <https://www.baeldung.com/>
