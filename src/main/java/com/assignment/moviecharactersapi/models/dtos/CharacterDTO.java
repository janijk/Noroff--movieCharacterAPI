package com.assignment.moviecharactersapi.models.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class CharacterDTO {
    private int id;
    private String name;
    private String alias;
    private String gender;
    private String picture;
    private Set<Integer> movies;
}
