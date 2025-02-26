package com.example.project.people.jsonapi.mappers;

import com.example.project.people.entities.Person;
import com.example.project.people.jsonapi.requests.PersonRequest;
import com.example.project.people.jsonapi.responses.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "attributes.name", source = "name")
    //TODO: This just prevents infinite recursion but we'd probably want the pets in the response and maybe just the owner name in the pet response.
    @Mapping(target = "relationships.pets", ignore = true)
    PersonResponse personToPersonResponse(Person person);


    @Mapping(target = "name", source = "attributes.name")
    @Mapping(target = "pets", source = "relationships.pets")
    Person personRequestToPerson(PersonRequest personRequest);

    List<PersonResponse> personToPersonResponse(List<Person> people);
}