package com.assignment.moviecharactersapi.services;

import com.assignment.moviecharactersapi.exceptions.FranchiseNotFoundException;
import com.assignment.moviecharactersapi.models.Franchise;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FranchiseServiceImpl implements FranchiseService{
    private final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);
    private final FranchiseRepository franchiseRepository;
    private final MovieService movieService;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, MovieService movieService) {
        this.franchiseRepository = franchiseRepository;
        this.movieService = movieService;
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(()->new FranchiseNotFoundException(id));
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
    @Transactional
    public void deleteById(Integer id) {
        if (franchiseRepository.existsById(id)){
            Franchise fran = franchiseRepository.findById(id).get();
            fran.getMovies().forEach(m -> m.setFranchise(null));
            franchiseRepository.deleteById(id);
        }else {
            logger.warn("Franchise with ID: " + id + " doesn't exist");
        }
    }
    @Override
    public void delete(Franchise entity) {
        franchiseRepository.delete(entity);
    }
    @Override
    public Collection<Movie> getAllMoviesInFranchise(int franchiseId) {
        return franchiseRepository.getMovies(franchiseId);
    }
    @Override
    public void updateMoviesInFranchise(int franchiseId, Set<Integer> movieId) {
        Set<Movie> films = null;
        if (movieId != null) {
            films = movieId.stream()
                    .map(movieService::findById).collect(Collectors.toSet());
            films.forEach(m -> m.setFranchise(findById(franchiseId)));
            films.forEach(movieService::update);
        }
    }
}
