package com.example.project.audit.jsonapi.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.project.audit.jsonapi.attributes.AuditAttributes;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuditRequest {

    @JsonProperty("attributes")
    private AuditAttributes attributes;

}