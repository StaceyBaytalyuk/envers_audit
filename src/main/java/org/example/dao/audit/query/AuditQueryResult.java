package org.example.dao.audit.query;

import org.example.dao.audit.model.CustomerRevisionEntity;
import org.hibernate.envers.RevisionType;

public record AuditQueryResult<T>(T entity, CustomerRevisionEntity revision, RevisionType type) {
}
