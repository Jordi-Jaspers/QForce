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

import java.time.LocalDate;

/**
 * The domain class representing a Star Wars movie.
 *
 * @author QNH
 */
public interface Movie {

    /**
     * Returns the title.
     *
     * @return the title
     */
    String getTitle();

    /**
     * Returns the episode number.
     *
     * @return the episode number
     */
    Integer getEpisode();

    /**
     * Returns the name of the person who directed the movie.
     *
     * @return the name of the director
     */
    String getDirector();

    /**
     * The release date of the movie.
     *
     * @return the release date
     */
    LocalDate getReleaseDate();
}
