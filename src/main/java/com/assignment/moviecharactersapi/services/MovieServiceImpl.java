package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Character;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.repositories.CharacterRepository;
import com.assignment.moviecharactersapi.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;
    private final CharacterService characterService;

    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository, CharacterService characterService) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
        this.characterService = characterService;
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow();
    }
    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }
    @Override
    public Collection<Movie> findAllByName(String name) {
        return movieRepository.findAllByName(name);
    }
    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }
    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }
    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (movieRepository.existsById(id)){
            movieRepository.deleteById(id);
        }else {
            logger.warn("Movie with ID: " + id + " doesn't exist");
        }
    }
    @Override
    public void delete(Movie entity) {
        movieRepository.delete(entity);
    }
    @Override
    public void updateCharactersInMovie(int movieId, Set<Integer> characterId) {
        Set<Character> chrs = null;
        if (characterId != null) {
            chrs = characterId.stream()
                    .map(id -> characterService.findById(id)).collect(Collectors.toSet());
        }
        Movie film = findById(movieId);
        film.setCharacters(chrs);
        update(film);
    }
}
