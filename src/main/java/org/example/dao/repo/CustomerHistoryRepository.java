package org.example.dao.repo;

import org.example.dao.audit.query.AuditQueryResult;
import org.example.dao.audit.query.AuditQueryUtils;
import org.example.dao.entity.Customer;
import org.example.dao.entity.CustomerHistory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerHistoryRepository implements ICustomerHistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerHistory> listCustomerRevisions(UUID customerId) {

        // Create the Audit Reader. It uses the EntityManager, which will be opened when
        // starting the new Transation and closed when the Transaction finishes.
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        // Create the Query:
        AuditQuery auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(Customer.class, false, true)
                .add(AuditEntity.id().eq(customerId));

        // We don't operate on the untyped Results, but cast them into a List of AuditQueryResult:
        return AuditQueryUtils.getAuditQueryResults(auditQuery, Customer.class).stream()
                // Turn into the CustomerHistory Domain Object:
                .map(CustomerHistoryRepository::getCustomerHistory)
                // And collect the Results:
                .collect(Collectors.toList());
    }

    private static CustomerHistory getCustomerHistory(AuditQueryResult<Customer> auditQueryResult) {
        auditQueryResult.revision().fixTimezone();
        Customer customer = auditQueryResult.entity();
        return new CustomerHistory(
                customer.getId(),
                customer.getName(),
                auditQueryResult.revision(),
                auditQueryResult.type()
        );
    }

}