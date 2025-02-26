package com.example.project.people.jsonapi.responses;

import com.example.project.people.jsonapi.attributes.PetAttributes;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Type("pets")
public class PetResponse {

    @Id
    private Long id;

    @JsonProperty("relationships")
    private Relationships relationships;

    @JsonProperty("attributes")
    private PetAttributes attributes;

    @Setter
    @Getter
    public static class Relationships {
        @JsonProperty("owner")
        private OwnerRelationship owner;
    }

    @Setter
    @Getter
    public static class OwnerRelationship {
        @JsonProperty("data")
        private PersonResponse data;
    }
}
