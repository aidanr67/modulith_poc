package com.example.project.audit.jsonapi.attributes;

public class AuditAttributes {
    private String action;
    private Long entityId;
    private String entityType;


    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityType() {
        return entityType;
    }

    public AuditAttributes() {}

    public AuditAttributes(String action, Long entityId, String entityType) {
        this.action = action;
        this.entityId = entityId;
        this.entityType = entityType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}