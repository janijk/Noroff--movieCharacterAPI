package com.assignment.moviecharactersapi.controllers;

import com.assignment.moviecharactersapi.mappers.MovieMapper;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.models.dtos.MovieDTO;
import com.assignment.moviecharactersapi.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

/**
 * Controller for endpoints considering Movies,
 * path begins with api/v1/movies
 */
@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public ResponseEntity getAll(){
        Collection<MovieDTO> films = movieMapper.movieToMovieDTO(
                movieService.findAll()
        );
        return ResponseEntity.ok(films);
    }
    @GetMapping("{id}") // GET : api/v1/movies/movieId
    public ResponseEntity getById(@PathVariable int id){
        MovieDTO film = movieMapper.movieToMovieDTO(
                movieService.findById(id)
        );
        return ResponseEntity.ok(film);
    }
    @PostMapping
    public ResponseEntity add(@RequestBody Movie movie){
        Movie film = movieService.add(movie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("{id}") // PUT : api/v1/movies/movieId
    public ResponseEntity update(@RequestBody MovieDTO movieDTO, @PathVariable int id){
        if (id != movieDTO.getId()){
            return ResponseEntity.badRequest().build();
        }else {
            movieService.update(
                    movieMapper.movieDTOtoMovie(movieDTO)
            );
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}") // DELETE : api/v1/movies/movieId
    public ResponseEntity delete(@PathVariable int id){
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
