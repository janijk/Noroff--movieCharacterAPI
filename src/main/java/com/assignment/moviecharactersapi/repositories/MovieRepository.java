package com.assignment.moviecharactersapi.repositories;

import com.assignment.moviecharactersapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    /**
     * Deletes all movie_character linking tables which have
     * this movies id as foreign key
     *
     * @param id must not be {@literal null}.
     */
    @Query(value = "DELETE FROM movie_characters WHERE movies_id=?",
    nativeQuery = true)
    void deleteByIdFromMovieCharacter(Integer id);
    /**
     * Inserts entry to movie_characters linking table in order to
     * add characters to a movie or movies to a character
     *
     * @param characterId id of a character
     * @param movieId id of a movie
     */
    @Query(value = "INSERT INTO movie_characters (characters_id, movies_id) VALUES (?,?)",
            nativeQuery = true)
    void addCharacterToMovie(Integer characterId, Integer movieId);
}
