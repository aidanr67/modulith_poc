package com.example.project.people.jsonapi.mappers;

import com.example.project.people.entities.Pet;
import com.example.project.people.jsonapi.requests.PetRequest;
import com.example.project.people.jsonapi.responses.PetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {PersonMapper.class})
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "attributes.name", source = "name")
    @Mapping(target = "relationships.owner.data", source = "owner")
    PetResponse petToPetResponse(Pet pet);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "attributes.name")
    @Mapping(target = "owner", source = "relationships.owner.data")
    Pet petRequestToPet(PetRequest petRequest);

    List<PetResponse> petToPetResponse(List<Pet> pets);
}
