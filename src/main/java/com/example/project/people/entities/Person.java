package com.example.project.people.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //TODO: This just prevents infinite recursion in the mapper, see TODO comment there.
    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}
