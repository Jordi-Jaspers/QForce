/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.qnh.qforce.domain;

import java.util.List;

/**
 * The domain class representing a Star Wars person.
 *
 * @author QNH
 */
public interface Person {

    /**
     * Returns the unique id of the person.
     *
     * @return the unique id of the person
     */
    long getId();

    /**
     * Retuns the name of the person.
     *
     * @return the name of the person
     */
    String getName();

    /**
     * Retuns the birth year of the person.
     *
     * @return the birth year of the person
     */
    String getBirthYear();

    /**
     * Retuns the gender of the person.
     *
     * @return the gender of the person
     */
    Gender getGender();

    /**
     * Retuns the height of the person in centimeters.
     *
     * @return the height of the person
     */
    Integer getHeight();

    /**
     * Retuns the weight of the person in kilograms.
     *
     * @return the weight of the person
     */
    Integer getWeight();

    /**
     * Returns the movies the person has been in.
     *
     * @return the movies the person has been in
     */
    List<Movie> getMovies();
}
