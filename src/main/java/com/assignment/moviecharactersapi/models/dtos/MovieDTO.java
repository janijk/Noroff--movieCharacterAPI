package com.assignment.moviecharactersapi.models.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class MovieDTO {
    private int id;
    private String title;
    private  String genre;
    private int year;
    private String director;
    private String picture;
    private String trailer;
    private Set<Integer> characters;
    private int franchise;
}
