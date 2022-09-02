package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Character;
import com.assignment.moviecharactersapi.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CharacterServiceImpl implements CharacterService{
    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
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

    }
    @Override
    public void delete(Character entity) {

    }
}
