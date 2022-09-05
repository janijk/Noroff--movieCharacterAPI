package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Movie;

import java.util.Collection;
import java.util.Set;

public interface MovieService extends CRUDService<Movie, Integer> {
    /**
     * Updates characters of a movie. Takes movie and array of
     * characters as parameters.
     *
     * @param movieId id of a movie
     * @param characterId array of character id's
     */
    void updateCharactersInMovie(int movieId, Set<Integer> characterId);
    Collection<Movie> findAllByName(String name);

}
