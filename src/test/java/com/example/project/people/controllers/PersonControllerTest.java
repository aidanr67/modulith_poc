package com.example.project.people.controllers;

import com.example.project.people.jsonapi.attributes.PersonAttributes;
import com.example.project.people.jsonapi.requests.PersonRequest;
import com.example.project.people.jsonapi.responses.PersonResponse;
import com.example.project.people.services.PersonService;
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

public class PersonControllerTest {

    @Mock
    private PersonService personService;


    @InjectMocks
    private PersonController personController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPeople() {
        // Arrange
        PersonAttributes person1Attributes = new PersonAttributes("John Doe");
        PersonAttributes person2Attributes = new PersonAttributes("John Doe");
        PersonResponse person1 = new PersonResponse(1L, person1Attributes, null);
        PersonResponse person2 = new PersonResponse(2L, person2Attributes, null);
        List<PersonResponse> people = Arrays.asList(person1, person2);

        when(personService.getAllPeopleResponse()).thenReturn(people);

        // Act
        ResponseEntity<List<PersonResponse>> response = personController.getAllPeople();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(people, response.getBody());
    }

    @Test
    public void testGetPersonById() {
        // Arrange
        Long personId = 1L;
        PersonAttributes personAttributes = new PersonAttributes("John Doe");
        PersonResponse personResponse = new PersonResponse(personId, personAttributes, null);

        when(personService.getPersonById(personId)).thenReturn(personResponse);

        // Act
        ResponseEntity<PersonResponse> response = personController.getPersonById(personId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personResponse, response.getBody());
    }

    @Test
    public void testCreatePerson() {
        // Arrange
        PersonAttributes personAttributes = new PersonAttributes("John Doe");
        PersonRequest personRequest = new PersonRequest();
        personRequest.setAttributes(personAttributes);
        PersonResponse personResponse = new PersonResponse(1L, personAttributes, null);

        when(personService.createPerson(personRequest)).thenReturn(personResponse);

        // Act
        ResponseEntity<PersonResponse> response = personController.createPerson(personRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(personResponse, response.getBody());
    }
}