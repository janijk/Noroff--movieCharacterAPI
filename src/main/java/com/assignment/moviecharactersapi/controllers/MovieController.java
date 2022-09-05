package com.assignment.moviecharactersapi.controllers;

import com.assignment.moviecharactersapi.exceptions.MovieNotFoundException;
import com.assignment.moviecharactersapi.mappers.MovieMapper;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.models.dtos.MovieDTO;
import com.assignment.moviecharactersapi.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "No movies found",
                    content = @Content)
    })
    @Operation(summary = "Get all movies")
    @GetMapping
    public ResponseEntity getAll(){
        Collection<MovieDTO> films = movieMapper.movieToMovieDTO(
                movieService.findAll()
        );
        return ResponseEntity.ok(films);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie doesn't exist with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Get a movie by id")
    @GetMapping("{id}") // GET : api/v1/movies/movieId
    public ResponseEntity getById(@PathVariable int id){
        MovieDTO film = movieMapper.movieToMovieDTO(
                movieService.findById(id)
        );
        return ResponseEntity.ok(film);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class))) })
    })
    @Operation(summary = "Get a movie by name")
    @GetMapping("search") // GET : api/v1/movie/search?name=movieName
    public ResponseEntity getByName(@RequestParam String name){
        Collection<MovieDTO> films = movieMapper.movieToMovieDTO(
                movieService.findAllByName(name)
        );
        if (films.isEmpty()){
            throw new MovieNotFoundException(name);
        }
        return ResponseEntity.ok(films);
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "Movie added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content)
    })
    @Operation(summary = "Add a new movie")
    @PostMapping // POST : api/v1/movies
    public ResponseEntity add(@RequestBody MovieDTO movie){
        movieService.add(
                movieMapper.movieDTOtoMovie(movie)
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movie updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Movie not found with supplied ID",
                    content = @Content)
    })
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

    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Characters updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Character not found with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Update characters in a movie")
    @PostMapping("characters/{id}") // PUT : api/v1/movies/characters/movieId
    public ResponseEntity updateAllCharacters(@PathVariable int id, @RequestBody Set<Integer> ids){
        movieService.updateCharactersInMovie(id, ids);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content)
    })
    @Operation(summary = "Delete a movie")
    @DeleteMapping("{id}") // DELETE : api/v1/movies/movieId
    public ResponseEntity delete(@PathVariable int id){
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
