package com.assignment.moviecharactersapi.controllers;

import com.assignment.moviecharactersapi.mappers.MovieMapper;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.models.dtos.MovieDTO;
import com.assignment.moviecharactersapi.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

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

    @Operation(summary = "Get all movies")
    @GetMapping
    public ResponseEntity getAll(){
        Collection<MovieDTO> films = movieMapper.movieToMovieDTO(
                movieService.findAll()
        );
        return ResponseEntity.ok(films);
    }
    @Operation(summary = "Get a movie by id")
    @GetMapping("{id}") // GET : api/v1/movies/movieId
    public ResponseEntity getById(@PathVariable int id){
        MovieDTO film = movieMapper.movieToMovieDTO(
                movieService.findById(id)
        );
        return ResponseEntity.ok(film);
    }
    @Operation(summary = "Get a movie by name")
    @GetMapping("search") // GET : api/v1/movie/search?name=movieName
    public ResponseEntity getByName(@RequestParam String name){
        Collection<MovieDTO> films = movieMapper.movieToMovieDTO(
                movieService.findAllByName(name)
        );
        return ResponseEntity.ok(films);
    }
    @Operation(summary = "Add a new movie")
    @PostMapping // POST : api/v1/movies
    public ResponseEntity add(@RequestBody Movie movie){
        Movie film = movieService.add(movie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Update a movie")
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
    @Operation(summary = "Update characters in a movie")
    @PostMapping("characters/{id}") // PUT : api/v1/movies/characters/movieId
    public ResponseEntity updateAllCharacters(@PathVariable int id, @RequestBody Set<Integer> ids){
        movieService.updateCharactersInMovie(id, ids);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Delete a movie")
    @DeleteMapping("{id}") // DELETE : api/v1/movies/movieId
    public ResponseEntity delete(@PathVariable int id){
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
