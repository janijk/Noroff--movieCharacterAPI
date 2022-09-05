package com.assignment.moviecharactersapi.mappers;

import com.assignment.moviecharactersapi.models.Franchise;
import com.assignment.moviecharactersapi.models.Movie;
import com.assignment.moviecharactersapi.models.Character;
import com.assignment.moviecharactersapi.models.dtos.MovieDTO;
import com.assignment.moviecharactersapi.models.dtos.MoviePOSTDTO;
import com.assignment.moviecharactersapi.services.CharacterService;
import com.assignment.moviecharactersapi.services.FranchiseService;
import com.assignment.moviecharactersapi.services.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    @Autowired
    MovieService movieService;
    @Autowired
    CharacterService characterService;
    @Autowired
    FranchiseService franchiseService;

    @Mapping(target = "franchise", source = "franchise.id")
    @Mapping(target = "characters", source = "characters", qualifiedByName = "charactersToIds")
    public abstract MovieDTO movieToMovieDTO(Movie movie);
    public abstract Collection<MovieDTO> movieToMovieDTO(Collection<Movie> movies);
    @Mapping(target = "franchise", source = "franchise", qualifiedByName = "franchisesIdToFranchise")
    @Mapping(target = "characters", source = "characters", qualifiedByName = "characterIdsToCharacters")
    public abstract Movie movieDTOtoMovie(MovieDTO movieDTO);
    @Mapping(target = "franchise", ignore = true)
    @Mapping(target = "characters", ignore = true)
    public abstract Movie moviePostDtoToMovie(MoviePOSTDTO mpdto);


    @Named("franchisesIdToFranchise")
    Franchise mapFranchiseIdToFranchise(int id){
        if(id != 0){
            return franchiseService.findById(id);
        }
        return null;
    }
    @Named("characterIdsToCharacters")
    Set<Character> mapCharacterIdsToCharacters(Set<Integer> ids){
        if (ids != null){
            return ids.stream().map(id -> characterService.findById(id)).collect(Collectors.toSet());
        }
        return null;
    }
    @Named("charactersToIds")
    Set<Integer> mapCharactersToIds(Set<Character> chars){
        if (chars != null){
            return chars.stream().map(c -> c.getId()).collect(Collectors.toSet());
        }
        return null;
    }
}
