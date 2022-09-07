package com.assignment.moviecharactersapi.models.dtos;

import lombok.Data;

/**
 * DTO for adding a new franchise. Franchise is added without movies.
 * Movies are added with update methods.
 */
@Data
public class FranchisePOSTDTO {
    private int id;
    private String name;
    private String description;
}
