package com.assignment.moviecharactersapi.models.dtos;

import lombok.Data;

@Data
public class CharacterPOSTDTO {
    private int id;
    private String name;
    private String alias;
    private String gender;
    private String picture;
}
