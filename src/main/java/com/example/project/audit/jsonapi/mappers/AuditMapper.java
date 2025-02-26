package com.example.project.audit.jsonapi.mappers;

import com.example.project.audit.entities.Audit;
import com.example.project.audit.jsonapi.requests.AuditRequest;
import com.example.project.audit.jsonapi.responses.AuditResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuditMapper {

    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "attributes.action", source = "action")
    @Mapping(target = "attributes.entityId", source = "entityId")
    @Mapping(target = "attributes.entityType", source = "entityType")
    AuditResponse auditToAuditResponse(Audit audit);

    List<AuditResponse> auditToAuditResponse(List<Audit> audits);
}