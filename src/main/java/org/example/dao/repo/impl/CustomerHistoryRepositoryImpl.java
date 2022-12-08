package org.example.dao.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.dao.audit.query.AuditQueryResult;
import org.example.dao.audit.query.AuditQueryUtils;
import org.example.dao.entity.CustomerEntity;
import org.example.dao.entity.CustomerHistoryEntity;
import org.example.dao.repo.CustomerHistoryRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerHistoryRepositoryImpl implements CustomerHistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerHistoryEntity> listCustomerRevisions(UUID customerId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(CustomerEntity.class, false, true)
                .add(AuditEntity.id().eq(customerId));

        return AuditQueryUtils.getAuditQueryResults(auditQuery, CustomerEntity.class).stream()
                .map(CustomerHistoryRepositoryImpl::getCustomerHistory)
                .toList();
    }

    private static CustomerHistoryEntity getCustomerHistory(AuditQueryResult<CustomerEntity> auditQueryResult) {
        CustomerEntity customerEntity = auditQueryResult.entity();
        return new CustomerHistoryEntity(
                customerEntity.getId(),
                customerEntity.getName(),
                customerEntity.getCity(),
                auditQueryResult.revision(),
                auditQueryResult.type()
        );
    }

}