package com.assignment.moviecharactersapi.controllers;

import com.assignment.moviecharactersapi.exceptions.MovieNotFoundException;
import com.assignment.moviecharactersapi.mappers.CharacterMapper;
import com.assignment.moviecharactersapi.mappers.FranchiseMapper;
import com.assignment.moviecharactersapi.mappers.MovieMapper;
import com.assignment.moviecharactersapi.models.dtos.*;
import com.assignment.moviecharactersapi.services.CharacterService;
import com.assignment.moviecharactersapi.services.FranchiseService;
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

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Controller for endpoints considering Characters,
 * path begins with api/v1/characters
 */
@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "No characters found",
                    content = @Content)
    })
    @Operation(summary = "Get all characters")
    @GetMapping
    public ResponseEntity getAll(){
        Collection<CharacterDTO> characters = characterMapper.characterToCharacterDTO(
                characterService.findAll()
        );
        return ResponseEntity.ok(characters);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Character doesn't exist with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Get a character by id")
    @GetMapping("{id}") // GET : api/v1/characters/characterId
    public ResponseEntity getById(@PathVariable int id){
        CharacterDTO character = characterMapper.characterToCharacterDTO(
                characterService.findById(id)
        );
        return ResponseEntity.ok(character);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie doesn't exist with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Get all characters in a movie")
    @GetMapping("movie/{id}") // GET : api/v1/characters/movie/movieId
    public ResponseEntity getCharactersByMovie(@PathVariable int id){
        Collection<CharacterDTO> characters = characterMapper.characterToCharacterDTO(
                characterService.findAllByMovieId(id)
        );
        return ResponseEntity.ok(characters);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise doesn't exist with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Get all characters in a franchise")
    @GetMapping("franchise/{id}") // GET : api/v1/characters/franchise/franchiseId
    public ResponseEntity getCharactersByFranchise(@PathVariable int id){
        Collection<CharacterDTO> characters = characterMapper.characterToCharacterDTO(
                characterService.findAllByFranchiseId(id)
        );
        return ResponseEntity.ok(characters);
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Character updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Character not found with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Update a character")
    @PostMapping("{id}") // PUT : api/v1/characters/characterId
    public ResponseEntity update(@RequestBody CharacterPOSTDTO characterPostDTO, @PathVariable int id){
        if (id != characterPostDTO.getId()){
            return ResponseEntity.badRequest().build();
        }else {
            characterService.update(
                    characterMapper.characterPostDTOToCharacter(characterPostDTO)
            );
        }
        return ResponseEntity.noContent().build();
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "Character added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content)
    })
    @Operation(summary = "Add a new character")
    @PostMapping // POST : api/v1/characters
    public ResponseEntity add(@RequestBody CharacterPOSTDTO character){
        characterService.add(
                characterMapper.characterPostDTOToCharacter(character)
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content)
    })
    @Operation(summary = "Delete a character")
    @DeleteMapping("{id}") // DELETE : api/v1/characters/characterId
    public ResponseEntity delete(@PathVariable int id){
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
