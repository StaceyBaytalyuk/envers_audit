package org.example.dao.audit.query;

import lombok.NoArgsConstructor;
import org.example.dao.audit.model.CustomRevisionEntity;
import org.hibernate.envers.RevisionType;

@NoArgsConstructor
public class AuditQueryResultUtils {
    public static <T> AuditQueryResult<T> getAuditQueryResult(Object[] item, Class<T> type) {
        if (item == null) {
            return null;
        }

        // not enough data
        if (item.length < 3) {
            return null;
        }

        T entity = null;
        if (type.isInstance(item[0])) {
            entity = type.cast(item[0]);
        }

        CustomRevisionEntity revision = null;
        if (item[1] instanceof CustomRevisionEntity) {
            revision = (CustomRevisionEntity) item[1];
        }

        RevisionType revisionType = null;
        if (item[2] instanceof RevisionType) {
            revisionType = (RevisionType) item[2];
        }

        return new AuditQueryResult<>(entity, revision, revisionType);
    }
}
