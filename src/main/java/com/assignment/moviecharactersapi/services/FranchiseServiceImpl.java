package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.models.Franchise;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.repositories.FranchiseRepository;
import com.assignment.moviecharactersapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FranchiseServiceImpl implements FranchiseService{
    private final FranchiseRepository franchiseRepository;
    private final MovieRepository movieRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow();
    }
    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }
    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }
    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }
    @Override
    public void deleteById(Integer id) {
        Franchise fran = franchiseRepository.findById(id).get();
        fran.getMovies().forEach(m -> m.setFranchise(null));
        franchiseRepository.deleteById(id);
    }
    @Override
    public void delete(Franchise entity) {

    }
    @Override
    public Collection<Movie> getAllMoviesInFranchise(int franchiseId) {
        return franchiseRepository.findById(franchiseId).get().getMovies();
    }
    @Override
    public Collection<Character> getAllCharactersInFranchise(int franchiseId) {
        Collection<Movie> films = getAllMoviesInFranchise(franchiseId);
        Collection<Character> chars = new ArrayList<>();
        for (Movie m : films){
            
        }
               // films.forEach(film ->chars.add(film.getCharacters()));
        return null;
    }
    @Override
    public void updateMoviesInFranchise(int franchiseId, int... movieId) {

    }
}
