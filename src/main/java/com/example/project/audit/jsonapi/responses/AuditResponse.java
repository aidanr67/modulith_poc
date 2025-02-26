package com.example.project.audit.jsonapi.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import com.example.project.audit.jsonapi.attributes.AuditAttributes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Type("audits")
public class AuditResponse {

    @Id
    private Long id;

    @JsonProperty("attributes")
    private AuditAttributes attributes;

    public AuditResponse(Long id, String action, Long entityId, String entityType) {
        this.id = id;
        this.attributes = new AuditAttributes(action, entityId, entityType);
    }

}