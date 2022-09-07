package com.assignment.moviecharactersapi.repositories;

import com.assignment.moviecharactersapi.models.Franchise;
import com.assignment.moviecharactersapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
    @Query("SELECT m FROM Movie m WHERE m.franchise.id = ?1")
    Set<Movie> getMovies(int franchiseId);
}
