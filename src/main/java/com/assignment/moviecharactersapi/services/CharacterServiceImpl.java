package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Character;
import com.assignment.moviecharactersapi.repositories.CharacterRepository;
import com.assignment.moviecharactersapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CharacterServiceImpl implements CharacterService{
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
    public void deleteById(Integer integer) {
        if(characterRepository.findById(integer).get().getMovies() != null){
            characterRepository.deleteByIdFromMovieCharacter(integer);
        }
        characterRepository.deleteById(integer);
    }
    @Override
    public void delete(Character entity) {
        characterRepository.delete(entity);
    }
    @Override
    public Collection<Character> findAllByMovieId(int movieId) {
        return movieRepository.findById(movieId).get().getCharacters();
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
