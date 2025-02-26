package com.example.project.people.controllers;

import com.example.project.people.jsonapi.attributes.PetAttributes;
import com.example.project.people.jsonapi.requests.PetRequest;
import com.example.project.people.jsonapi.responses.PetResponse;
import com.example.project.people.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllPets() {
        // Arrange
        PetAttributes pet1Attributes = new PetAttributes("Buddy");
        PetAttributes pet2Attributes = new PetAttributes("Mittens");

        PetResponse pet1 = new PetResponse(1L, null, pet1Attributes);
        PetResponse pet2 = new PetResponse(2L, null, pet2Attributes);
        List<PetResponse> pets = Arrays.asList(pet1, pet2);

        when(petService.getAllPetsResponse()).thenReturn(pets);

        // Act
        ResponseEntity<List<PetResponse>> response = petController.getAllPets();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pets, response.getBody());
    }

    @Test
    public void testCreatePet() {
        // Arrange
        PetAttributes petAttributes = new PetAttributes("Buddy");
        PetRequest petRequest = new PetRequest();
        petRequest.setAttributes(petAttributes);
        PetResponse petResponse = new PetResponse(1L, null, petAttributes);

        when(petService.createPet(petRequest)).thenReturn(petResponse);

        // Act
        ResponseEntity<PetResponse> response = petController.createPet(petRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(petResponse, response.getBody());
    }
}