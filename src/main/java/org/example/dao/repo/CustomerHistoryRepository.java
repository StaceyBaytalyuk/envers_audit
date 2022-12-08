package org.example.dao.repo;

import org.example.dao.entity.CustomerHistoryEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerHistoryRepository {
    List<CustomerHistoryEntity> listCustomerRevisions(UUID customerId);
}
