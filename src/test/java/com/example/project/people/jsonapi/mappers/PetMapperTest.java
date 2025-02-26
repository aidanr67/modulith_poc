package com.example.project.people.jsonapi.mappers;

import com.example.project.people.entities.Person;
import com.example.project.people.entities.Pet;
import com.example.project.people.jsonapi.requests.PetRequest;
import com.example.project.people.jsonapi.attributes.PetAttributes;
import com.example.project.people.jsonapi.requests.PetRequest.Relationships;
import com.example.project.people.jsonapi.responses.PetResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetMapperTest {

    @Test
    void petToPetResponse() {
        Person owner = new Person(1L, "John Doe", null);
        Pet pet = new Pet(1L, "Buddy", owner);

        PetResponse response = PetMapper.INSTANCE.petToPetResponse(pet);

        assertEquals(pet.getId(), response.getId());
        assertEquals(pet.getName(), response.getAttributes().getName());
        //TODO: add relationship assertions.
    }

    @Test
    void petRequestToPet() {
        PetAttributes attributes = new PetAttributes("Buddy");

        Relationships relationships = new Relationships();

        PetRequest request = new PetRequest();
        request.setAttributes(attributes);
        request.setRelationships(relationships);

        Pet pet = PetMapper.INSTANCE.petRequestToPet(request);

        assertEquals(request.getAttributes().getName(), pet.getName());
        //TODO: add relationship assertions.
    }

    @Test
    void petToPetResponseList() {
        Person owner = new Person(1L, "John Doe", null);
        Pet pet1 = new Pet(1L, "Buddy", owner);
        Pet pet2 = new Pet(2L, "Fluffy", owner);
        List<Pet> pets = Arrays.asList(pet1, pet2);

        List<PetResponse> responses = PetMapper.INSTANCE.petToPetResponse(pets);

        assertEquals(2, responses.size());
        assertEquals(pets.get(0).getId(), responses.get(0).getId());
        assertEquals(pets.get(1).getId(), responses.get(1).getId());
    }
}