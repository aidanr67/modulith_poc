package com.example.project.shared.events;

import com.example.project.audit.entities.Audit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class AuditEvent extends ApplicationEvent {
    private final Audit audit;

    public AuditEvent(Object source, String action, Long entityId, String entityType) {
        super(source);
        this.audit = new Audit(action, entityId, entityType);
    }
}