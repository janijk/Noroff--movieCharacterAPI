package com.assignment.moviecharactersapi.controllers;

import com.assignment.moviecharactersapi.mappers.FranchiseMapper;
import com.assignment.moviecharactersapi.models.dtos.FranchiseDTO;
import com.assignment.moviecharactersapi.models.dtos.FranchisePOSTDTO;
import com.assignment.moviecharactersapi.services.FranchiseService;
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
import java.util.Set;

/**
 * Controller for endpoints considering Franchises,
 * path begins with api/v1/franchises
 */
@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;

    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "No franchises found",
                    content = @Content)
    })
    @Operation(summary = "Get all franchises")
    @GetMapping
    public ResponseEntity getAll(){
        Collection<FranchiseDTO> franchises = franchiseMapper.franchiseToFranchiseDTO(
                franchiseService.findAll()
        );
        return ResponseEntity.ok(franchises);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise doesn't exist with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Get a franchise by id")
    @GetMapping("{id}") // GET : api/v1/franchises/franchiseId
    public ResponseEntity getById(@PathVariable int id){
        FranchiseDTO film = franchiseMapper.franchiseToFranchiseDTO(
                franchiseService.findById(id)
        );
        return ResponseEntity.ok(film);
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Franchise updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Update a franchise")
    @PostMapping("{id}") // PUT : api/v1/franchises/franchiseId
    public ResponseEntity update(@RequestBody FranchiseDTO franchiseDTO, @PathVariable int id){
        if (id != franchiseDTO.getId()){
            return ResponseEntity.badRequest().build();
        }else {
            franchiseService.update(
                    franchiseMapper.franchiseDTOtoFranchise(franchiseDTO)
            );
        }
        return ResponseEntity.noContent().build();
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "Franchise added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content)
    })
    @Operation(summary = "Add a new franchise")
    @PostMapping // POST : api/v1/franchises
    public ResponseEntity add(@RequestBody FranchisePOSTDTO franchise){
        franchiseService.add(
                franchiseMapper.franchisePostDTOToFranchise(franchise)
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Movies updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Movie not found with supplied ID",
                    content = @Content)
    })
    @Operation(summary = "Update movies in a franchise")
    @PostMapping("movies/{id}") // PUT : api/v1/franchises/movies/franchiseId
    public ResponseEntity updateAllMovies(@PathVariable int id, @RequestBody Set<Integer> ids){
        franchiseService.updateMoviesInFranchise(id, ids);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content)
    })
    @Operation(summary = "Delete a franchise")
    @DeleteMapping("{id}") // DELETE : api/v1/franchises/franchiseId
    public ResponseEntity delete(@PathVariable int id){
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
