package org.example.dao.audit.query;

import org.example.dao.audit.model.CustomRevisionEntity;
import org.hibernate.envers.RevisionType;

public record AuditQueryResult<T>(T entity, CustomRevisionEntity revision, RevisionType type) {
}
