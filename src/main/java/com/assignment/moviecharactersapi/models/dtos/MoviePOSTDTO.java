package com.assignment.moviecharactersapi.models.dtos;

import lombok.Data;
/**
 * DTO for adding a new movie. Movie is added without characters or a franchise.
 * Characters and franchise are added with update methods.
 */
@Data
public class MoviePOSTDTO {
    private int id;
    private String title;
    private  String genre;
    private int year;
    private String director;
    private String picture;
    private String trailer;
}
