package org.example.dao.repo;

import org.example.dao.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ICustomerRepository extends CrudRepository<Customer, UUID> {
}
