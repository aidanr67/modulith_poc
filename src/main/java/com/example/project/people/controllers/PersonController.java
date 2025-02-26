package com.example.project.people.controllers;

import com.example.project.people.jsonapi.requests.PersonRequest;
import com.example.project.people.jsonapi.responses.PersonResponse;
import com.example.project.people.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonResponse>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllPeopleResponse());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest) {
        PersonResponse createdPerson = personService.createPerson(personRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }
}