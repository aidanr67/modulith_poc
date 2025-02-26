package com.example.project.people.jsonapi.responses;

import com.example.project.people.jsonapi.attributes.PersonAttributes;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Type("people")
public class PersonResponse {
    @Id
    private Long id;

    @JsonProperty("attributes")
    private PersonAttributes attributes;

    @JsonProperty("relationships")
    private Relationships relationships;

    @Getter
    @Setter
    public static class Relationships {
        @JsonProperty("pets")
        private List<PetResponse> pets;
    }
}