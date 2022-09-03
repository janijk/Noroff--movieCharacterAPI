package com.assignment.moviecharactersapi.repositories;

import com.assignment.moviecharactersapi.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    /**
     * Deletes all movie_character linking tables which have
     * this character id as foreign key
     *
     * @param id must not be {@literal null}.
     */
    @Query(value = "DELETE FROM movie_characters WHERE characters_id=?",
            nativeQuery = true)
    void deleteByIdFromMovieCharacter(Integer id);
    /**
     * Get all characters that appear in a franchise,
     * returns list of character id's
     *
     * @param franchiseId id of a franchise
     * @return
     */
    @Query(value = "SELECT DISTINCT movie_characters.characters_id " +
            "FROM (movie_characters " +
            "INNER JOIN movie ON movie.id = movie_characters.movies_id) " +
            "WHERE franchise_id=?",
    nativeQuery = true)
    Collection<Integer> findCharactersByFranchiseId(Integer franchiseId);

}
