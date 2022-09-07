package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.exceptions.MovieNotFoundException;
import com.assignment.moviecharactersapi.models.Character;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.repositories.CharacterRepository;
import com.assignment.moviecharactersapi.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CharacterServiceImpl implements CharacterService{
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).orElseThrow();
    }
    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }
    @Override
    public Character add(Character entity) {
        return characterRepository.save(entity);
    }
    @Override
    public Character update(Character entity) {
        return characterRepository.save(entity);
    }
    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (characterRepository.existsById(id)){
            characterRepository.deleteById(id);
        }else {
            logger.warn("Character with ID: " + id + " doesn't exist");
        }
    }
    @Override
    public void delete(Character entity) {
        characterRepository.delete(entity);
    }
    @Override
    public Collection<Character> findAllByMovieId(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        return movie.getCharacters();
    }
    @Override
    public Collection<Character> findAllByFranchiseId(int franchiseId) {
        Collection<Integer> ids = characterRepository.findCharactersByFranchiseId(franchiseId);
        Collection<Character> chars = new ArrayList<>();
        for (int i : ids){
            chars.add(characterRepository.findById(i).get());
        }
        return chars;
    }

}
