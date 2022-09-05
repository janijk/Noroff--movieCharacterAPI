package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Character;

import java.util.Collection;

public interface CharacterService extends CRUDService<Character, Integer>{
    /**
     * Get all characters that appear a given movie
     *
     * @param movieId id of a movie
     * @return Collection of characters
     */
    Collection<Character> findAllByMovieId(int movieId);

    /**
     * Get all characters that appear in a given franchise
     *
     * @param franchiseId id of a franchise
     * @return Collection of characters
     */
    Collection<Character> findAllByFranchiseId(int franchiseId);

}
