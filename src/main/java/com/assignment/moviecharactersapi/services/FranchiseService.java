package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Franchise;
import com.assignment.moviecharactersapi.models.Movie;

import java.util.Collection;

public interface FranchiseService extends CRUDService<Franchise, Integer> {
    /**
     * Get all movies that belong to a given franchise
     *
     * @param franchiseId id of a franchise
     * @return Collection of movies
     */
    Collection<Movie> getAllMoviesInFranchise(int franchiseId);
    /**
     * Get all characters that appear in a given franchise
     *
     * @param franchiseId id of a franchise
     * @return Collection of characters
     */
    Collection<Character> getAllCharactersInFranchise(int franchiseId);
    /**
     * Updates movies of a franchise. Takes franchise and array of
     * movies as parameters
     *
     * @param franchiseId id of a franchise
     * @param movieId array of movie id's
     */
    void updateMoviesInFranchise(int franchiseId, int... movieId);
}
