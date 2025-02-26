package com.example.project.people.jsonapi.requests;

import com.example.project.people.jsonapi.attributes.PetAttributes;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequest {

    @Id
    private Long id;

    @NotNull(message = "attributes is required")
    @Valid
    @JsonProperty("attributes")
    private PetAttributes attributes;

    @NotNull(message = "relationships is required")
    @Valid
    @JsonProperty("relationships")
    private Relationships relationships;

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
        private PersonRequest data;
    }
}
