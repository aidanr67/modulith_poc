package com.example.project.audit.controllers;

import com.example.project.audit.entities.Audit;
import com.example.project.audit.jsonapi.mappers.AuditMapper;
import com.example.project.audit.jsonapi.responses.AuditResponse;
import com.example.project.audit.repositories.AuditRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private AuditRepository auditRepository;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AuditResponse>> getAllAudits() {
        List<Audit> audits = auditRepository.findAll();

        return ResponseEntity.ok(AuditMapper.INSTANCE.auditToAuditResponse(audits));
    }
}