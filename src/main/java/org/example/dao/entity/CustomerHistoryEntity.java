package org.example.dao.entity;

import org.example.dao.audit.model.CustomRevisionEntity;
import org.hibernate.envers.RevisionType;

import java.util.UUID;

public record CustomerHistoryEntity(
        UUID customerId,
        String name,
        String city,
        CustomRevisionEntity revisionInfo,
        RevisionType revisionType) {
}
