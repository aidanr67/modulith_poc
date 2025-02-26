package com.example.project.people.services;

import com.example.project.people.constants.ResourceTypes;
import com.example.project.people.entities.Person;
import com.example.project.people.jsonapi.attributes.PersonAttributes;
import com.example.project.people.jsonapi.requests.PersonRequest;
import com.example.project.people.jsonapi.responses.PersonResponse;
import com.example.project.people.repositories.PersonRepository;
import com.example.project.shared.events.AuditAction;
import com.example.project.shared.events.AuditEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Captor
    private ArgumentCaptor<AuditEvent> auditEventCaptor;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPeopleResponse() {
        Person person1 = new Person(1L, "John", null);
        Person person2 = new Person(2L, "Jane", null);
        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        List<PersonResponse> personResponses = personService.getAllPeopleResponse();

        assertEquals(2, personResponses.size());

        verify(eventPublisher).publishEvent(auditEventCaptor.capture());
        AuditEvent auditEvent = auditEventCaptor.getValue();
        assertEquals(AuditAction.READ.name(), auditEvent.getAudit().getAction());
        assertNull(auditEvent.getAudit().getEntityId());
        assertEquals(ResourceTypes.PERSON, auditEvent.getAudit().getEntityType());
    }

    @Test
    void getPersonById_existingPerson() {
        Person person = new Person(1L, "John", null);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        PersonResponse personResponse = personService.getPersonById(1L);

        assertEquals("John", personResponse.getAttributes().getName());

        verify(eventPublisher).publishEvent(auditEventCaptor.capture());
        AuditEvent auditEvent = auditEventCaptor.getValue();
        assertEquals(AuditAction.READ.name(), auditEvent.getAudit().getAction());
        assertEquals(1L, auditEvent.getAudit().getEntityId());
        assertEquals(ResourceTypes.PERSON, auditEvent.getAudit().getEntityType());
    }

    @Test
    void getPersonById_nonExistingPerson() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        PersonResponse personResponse = personService.getPersonById(1L);

        assertNull(personResponse);
        verify(eventPublisher, never()).publishEvent(any());
    }

    @Test
    void createPerson() {
        // Arrange
        PersonRequest personRequest = new PersonRequest();
        personRequest.setId(1L);
        personRequest.setAttributes(new PersonAttributes("John"));

        Person person = new Person(1L, "John", null);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        // Act
        PersonResponse personResponse = personService.createPerson(personRequest);

        // Assert
        assertEquals(1L, personResponse.getId());
        assertEquals("John", personResponse.getAttributes().getName());

        verify(personRepository).save(any(Person.class));
        verify(eventPublisher).publishEvent(auditEventCaptor.capture());
        AuditEvent auditEvent = auditEventCaptor.getValue();
        assertEquals(AuditAction.CREATE.name(), auditEvent.getAudit().getAction());
        assertEquals(ResourceTypes.PERSON, auditEvent.getAudit().getEntityType());
    }
}