package org.example.dao.repo;

import org.example.dao.entity.CustomerHistory;

import java.util.List;
import java.util.UUID;

public interface ICustomerHistoryRepository {
    List<CustomerHistory> listCustomerRevisions(UUID customerId);
}
