package com.example.project.people.services;

import com.example.project.people.entities.Pet;
import com.example.project.people.jsonapi.requests.PetRequest;
import com.example.project.people.jsonapi.responses.PetResponse;
import com.example.project.people.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPetsResponse() {
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Fluffy");

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("Buddy");

        when(petRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        List<PetResponse> petResponses = petService.getAllPetsResponse();

        assertEquals(2, petResponses.size());
         assertEquals("Fluffy", petResponses.get(0).getAttributes().getName());
         assertEquals("Buddy", petResponses.get(1).getAttributes().getName());
    }

    @Test
    void createPet() {
        PetRequest petRequest = new PetRequest();

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Max");

        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        PetResponse petResponse = petService.createPet(petRequest);

        verify(petRepository).save(any(Pet.class));
        assertEquals("Max", petResponse.getAttributes().getName());
    }
}