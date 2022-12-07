package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.dao.audit.model.CustomRevisionEntity;
import org.example.dao.entity.Customer;
import org.example.dao.entity.CustomerHistory;
import org.example.dao.repo.ICustomerHistoryRepository;
import org.example.dao.repo.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ToString
public class CustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerHistoryRepository historyRepository;

    public void updateName(UUID id, String name) {
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer = optional.orElseThrow();
        customer.setName(name);
        customerRepository.save(customer);
        printHistory(id);
    }

    private void printHistory(UUID id) {
        List<CustomerHistory> history = historyRepository.listCustomerRevisions(id);
        System.out.println("++++++++++++++++++++++++++");
        history.forEach(x -> {
            CustomRevisionEntity rev = x.revisionInfo();
            System.out.println("revision №" + rev.getNumber() + ", " + rev.getRevisionDate());
            System.out.println("name=" + x.customerName() + ", id=" + x.customerId());
            System.out.println("=========");
        });
        System.out.println("++++++++++++++++++++++++++");
    }

}
