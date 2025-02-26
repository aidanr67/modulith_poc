package com.example.project.people.jsonapi.requests;

import com.example.project.people.jsonapi.responses.PetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.project.people.jsonapi.attributes.PersonAttributes;
import com.github.jasminb.jsonapi.annotations.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PersonRequest {
    @Id
    private Long id;

    @JsonProperty("attributes")
    private PersonAttributes attributes;

    @JsonProperty("relationships")
    private com.example.project.people.jsonapi.responses.PersonResponse.Relationships relationships;

    @Getter
    @Setter
    public static class Relationships {
        @JsonProperty("pets")
        private List<PetResponse> pets;
    }
}