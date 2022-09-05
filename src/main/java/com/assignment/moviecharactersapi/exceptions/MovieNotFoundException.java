package com.assignment.moviecharactersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(int id) {
        super("Movie with ID: " + id + " doesn't exist");
    }
    public MovieNotFoundException(String search){
        super("No movies found with search: " + search);
    }
}
