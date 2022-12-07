package org.example.dao.audit.query;

import lombok.NoArgsConstructor;
import org.hibernate.envers.query.AuditQuery;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AuditQueryUtils {
    public static <T> List<AuditQueryResult<T>> getAuditQueryResults(AuditQuery query, Class<T> targetType) {
        List<?> results = query.getResultList();

        if (results == null) {
            return new ArrayList<>();
        }

        // The AuditReader returns a List of Object[], where the indices are:
        // 0 - The queried entity
        // 1 - The revision entity
        // 2 - The Revision Type
        return results.stream()
                .filter(x -> x instanceof Object[])
                .map(x -> (Object[]) x)
                .map(x -> AuditQueryResultUtils.getAuditQueryResult(x, targetType))
                .toList();
    }
}
