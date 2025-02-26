package com.example.project.audit.services;

import com.example.project.shared.events.AuditEvent;
import com.example.project.audit.repositories.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    @EventListener
    public void handleAuditEvent(AuditEvent event) {
        auditRepository.save(event.getAudit());
    }
}