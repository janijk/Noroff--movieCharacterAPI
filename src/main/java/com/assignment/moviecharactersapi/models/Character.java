package com.assignment.moviecharactersapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50)
    private String alias;
    @Column(length = 50)
    private String gender;
    @Column(length = 200)
    private String picture;
    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;
}
