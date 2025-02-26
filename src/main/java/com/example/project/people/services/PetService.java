package com.example.project.people.services;

import com.example.project.people.entities.Pet;
import com.example.project.people.jsonapi.mappers.PetMapper;
import com.example.project.people.jsonapi.requests.PetRequest;
import com.example.project.people.jsonapi.responses.PetResponse;
import com.example.project.people.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<PetResponse> getAllPetsResponse() {
        List<Pet> pets = petRepository.findAll();

        return PetMapper.INSTANCE.petToPetResponse(pets);
    }

    public PetResponse createPet(PetRequest petRequest) {
        Pet pet = PetMapper.INSTANCE.petRequestToPet(petRequest);
        Pet savedPet = petRepository.save(pet);

        return PetMapper.INSTANCE.petToPetResponse(savedPet);
    }
}
