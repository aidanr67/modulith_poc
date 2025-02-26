package com.example.project.people.controllers;

import com.example.project.people.jsonapi.requests.PetRequest;
import com.example.project.people.jsonapi.responses.PetResponse;
import com.example.project.people.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PetResponse>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPetsResponse());
    }

    @PostMapping(consumes = "application/vnd.api+json", produces = "application/vnd.api+json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PetResponse> createPet(@RequestBody PetRequest petRequest) {
        PetResponse createdPet = petService.createPet(petRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }
}
