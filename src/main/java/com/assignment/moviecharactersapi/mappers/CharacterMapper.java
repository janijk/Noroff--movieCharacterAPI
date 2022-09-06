package com.assignment.moviecharactersapi.mappers;

import com.assignment.moviecharactersapi.models.Character;
import com.assignment.moviecharactersapi.models.Franchise;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.models.dtos.CharacterDTO;
import com.assignment.moviecharactersapi.models.dtos.CharacterPOSTDTO;
import com.assignment.moviecharactersapi.models.dtos.FranchiseDTO;
import com.assignment.moviecharactersapi.services.CharacterService;
import com.assignment.moviecharactersapi.services.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {
    @Autowired
    MovieService movieService;

    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO characterToCharacterDTO(Character character);
    public abstract Collection<CharacterDTO> characterToCharacterDTO(Collection<Character> characters);
    @Mapping(target = "movies", source = "movies", qualifiedByName = "movieIdsToMovies")
    public abstract Character characterDTOToCharacter(CharacterDTO characterDTO);
    @Mapping(target = "movies", ignore = true)
    public abstract Character characterPostDTOToCharacter(CharacterPOSTDTO characterPOSTDTO);

    @Named("moviesToIds")
    Set<Integer> mapMoviesToIds(Set<Movie> movies){
        if (movies != null){
            return movies.stream().map(m -> m.getId()).collect(Collectors.toSet());
        }
        return null;
    }

    @Named("movieIdsToMovies")
    Set<Movie> mapMovieIdsToMovies(Set<Integer> ids){
        if (ids != null){
            return ids.stream().map(id -> movieService.findById(id)).collect(Collectors.toSet());
        }
        return null;
    }
}
