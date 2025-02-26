package com.example.project.people.services;

import com.example.project.shared.events.AuditAction;
import com.example.project.shared.events.AuditEvent;

import com.example.project.people.constants.ResourceTypes;
import com.example.project.people.entities.Person;
import com.example.project.people.jsonapi.mappers.PersonMapper;
import com.example.project.people.jsonapi.requests.PersonRequest;
import com.example.project.people.jsonapi.responses.PersonResponse;
import com.example.project.people.repositories.PersonRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private static final Logger logger = Logger.getLogger(PersonService.class.getName());
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public List<PersonResponse> getAllPeopleResponse() {
        executorService.submit(() -> logger.info("Fetching all people"));
        List<Person> people = personRepository.findAll();
        eventPublisher.publishEvent(new AuditEvent(this, AuditAction.READ.name(), null, ResourceTypes.PERSON));
        return PersonMapper.INSTANCE.personToPersonResponse(people);
    }

    public PersonResponse getPersonById(Long id) {
        executorService.submit(() -> logger.info("Fetching person with ID: " + id));
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return null;
        }
        eventPublisher.publishEvent(new AuditEvent(this, AuditAction.READ.name(), person.getId(), ResourceTypes.PERSON));
        return PersonMapper.INSTANCE.personToPersonResponse(person);
    }

    public PersonResponse createPerson(PersonRequest personRequest) {
        Person person = PersonMapper.INSTANCE.personRequestToPerson(personRequest);
        executorService.submit(() -> logger.info("Creating person: " + person.getName()));
        Person savedPerson = personRepository.save(person);
        eventPublisher.publishEvent(new AuditEvent(this, AuditAction.CREATE.name(), savedPerson.getId(), ResourceTypes.PERSON));
        return PersonMapper.INSTANCE.personToPersonResponse(savedPerson);
    }
}