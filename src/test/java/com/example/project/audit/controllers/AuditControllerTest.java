package com.example.project.audit.controllers;

import com.example.project.audit.entities.Audit;
import com.example.project.audit.jsonapi.attributes.AuditAttributes;
import com.example.project.audit.jsonapi.mappers.AuditMapper;
import com.example.project.audit.jsonapi.responses.AuditResponse;
import com.example.project.audit.repositories.AuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuditControllerTest {

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private AuditMapper auditMapper;

    @InjectMocks
    private AuditController auditController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAudits() {
        // Arrange
        Audit audit1 = new Audit(1L, "CREATE", 100L, "Person");
        Audit audit2 = new Audit(2L, "UPDATE", 101L, "Person");
        List<Audit> audits = Arrays.asList(audit1, audit2);

        AuditResponse auditResponse1 = new AuditResponse();
        auditResponse1.setId(1L);
        auditResponse1.setAttributes(new AuditAttributes("CREATE", 100L, "Person"));

        AuditResponse auditResponse2 = new AuditResponse();
        auditResponse2.setId(2L);
        auditResponse2.setAttributes(new AuditAttributes("UPDATE", 101L, "Person"));

        List<AuditResponse> auditResponses = Arrays.asList(auditResponse1, auditResponse2);

        when(auditRepository.findAll()).thenReturn(audits);
        when(auditMapper.auditToAuditResponse(audits)).thenReturn(auditResponses);

        // Act
        ResponseEntity<List<AuditResponse>> response = auditController.getAllAudits();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        List<AuditResponse> responseBody = response.getBody();
        for (int i = 0; i < auditResponses.size(); i++) {
            assertEquals(auditResponses.get(i).getId(), responseBody.get(i).getId());
            assertEquals(auditResponses.get(i).getAttributes().getAction(), responseBody.get(i).getAttributes().getAction());
            assertEquals(auditResponses.get(i).getAttributes().getEntityId(), responseBody.get(i).getAttributes().getEntityId());
            assertEquals(auditResponses.get(i).getAttributes().getEntityType(), responseBody.get(i).getAttributes().getEntityType());
        }
    }
}