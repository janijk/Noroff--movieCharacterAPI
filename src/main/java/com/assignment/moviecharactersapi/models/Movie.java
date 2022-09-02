package com.assignment.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String genre;
    @Column(name = "release_year")
    private int year;
    @Column(length = 50, nullable = false)
    private String director;
    @Column(length = 100)
    private String picture;
    @Column(length = 100)
    private String trailer;
    @ManyToMany
    private Set<Character> characters;
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
}
