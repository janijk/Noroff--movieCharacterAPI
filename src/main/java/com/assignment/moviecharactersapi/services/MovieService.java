package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Character;
import com.assignment.moviecharactersapi.models.Movie;

import java.util.Collection;

public interface MovieService extends CRUDService<Movie, Integer> {
    /**
     * Get all characters that appear in a given movie
     *
     * @param movieId id of a movie
     * @return Collection of characters
     */
    Collection<Character> getAllCharactersInMovie(int movieId);
    /**
     * Updates characters of a movie. Takes movie and array of
     * characters as parameters.
     *
     * @param movieId id of a movie
     * @param characterId array of character id's
     */
    void updateCharactersInMovie(int movieId, int... characterId);
}
