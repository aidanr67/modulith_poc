package com.example.project.people.jsonapi.mappers;

import com.example.project.people.entities.Person;
import com.example.project.people.entities.Pet;
import com.example.project.people.jsonapi.requests.PersonRequest;
import com.example.project.people.jsonapi.attributes.PersonAttributes;
import com.example.project.people.jsonapi.responses.PersonResponse.Relationships;
import com.example.project.people.jsonapi.responses.PersonResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    @Test
    void personToPersonResponse() {
        Person person = new Person(1L, "John Doe", null);
        PersonResponse response = PersonMapper.INSTANCE.personToPersonResponse(person);

        assertEquals(person.getId(), response.getId());
        assertEquals(person.getName(), response.getAttributes().getName());
        assertNull(response.getRelationships().getPets());
    }

    @Test
    void personRequestToPerson() {
        PersonAttributes attributes = new PersonAttributes("John Doe");

        Relationships relationships = new Relationships();
        relationships.setPets(Collections.emptyList());

        PersonRequest request = new PersonRequest();
        request.setAttributes(attributes);
        request.setRelationships(relationships);

        Person person = PersonMapper.INSTANCE.personRequestToPerson(request);

        assertEquals(request.getAttributes().getName(), person.getName());
        assertTrue(person.getPets().isEmpty());
    }

    @Test
    void personToPersonResponseList() {
        Person person1 = new Person(1L, "John Doe", null);
        Person person2 = new Person(2L, "Jane Smith", null);
        List<Person> people = Arrays.asList(person1, person2);

        List<PersonResponse> responses = PersonMapper.INSTANCE.personToPersonResponse(people);

        assertEquals(2, responses.size());
        assertEquals(people.get(0).getId(), responses.get(0).getId());
        assertEquals(people.get(1).getId(), responses.get(1).getId());
    }
}