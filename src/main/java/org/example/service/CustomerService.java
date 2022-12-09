package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.controller.model.CreateCustomerRequest;
import org.example.converter.CustomerConverter;
import org.example.dao.audit.AuditAction;
import org.example.dao.audit.model.CustomerRevisionEntity;
import org.example.dao.entity.CustomerEntity;
import org.example.dao.entity.CustomerHistoryEntity;
import org.example.dao.repo.CustomerHistoryRepository;
import org.example.dao.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
@ToString
public class CustomerService {

    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;
    private final CustomerHistoryRepository historyRepository;

    private static final BiConsumer<CustomerEntity, String> setCustomerName = CustomerEntity::setName;
    private static final BiConsumer<CustomerEntity, String> setCustomerCity = CustomerEntity::setCity;
    private static final BiConsumer<CustomerEntity, String> setCustomerAge = CustomerEntity::setAge;

    public void updateName(UUID id, String name) {
        updateField(id, name, setCustomerName);
    }

    public void updateCity(UUID id, String city) {
        updateField(id, city, setCustomerCity);
    }

    public void updateAge(UUID id, String age) {
        updateField(id, age, setCustomerAge);
    }

    public void updateField(UUID id, String value, BiConsumer<CustomerEntity, String> setField) {
        Optional<CustomerEntity> optional = customerRepository.findById(id);

        if (optional.isEmpty()) {
            System.out.println("Can't update " + id + " customer: doesn't exist");
            return;
        }

        CustomerEntity customerEntity = optional.get();
        setField.accept(customerEntity, value);
        customerRepository.save(customerEntity);
        printHistory(id);
    }

    private void printHistory(UUID id) {
        List<CustomerHistoryEntity> history = historyRepository.listCustomerRevisions(id);
        System.out.println("++++++++++++++++++++++++++");
        history.forEach(x -> {
            CustomerRevisionEntity rev = x.revisionInfo();
            String action = AuditAction.getActionByCode(x.revisionType().getRepresentation()).getName();
            System.out.println("revision №" + rev.getNumber() + ", " + action + " a customer at " + rev.getTimestamp());
            System.out.println(x.name() + " from " + x.city() + ", id=" + x.customerId());
            System.out.println("=========");
        });
        System.out.println("++++++++++++++++++++++++++");
    }

    public UUID createCustomer(CreateCustomerRequest request) {
        CustomerEntity customer = customerConverter.convert(request);
        CustomerEntity savedCustomer = customerRepository.save(customer);
        UUID id = savedCustomer.getId();
        printHistory(id);
        return id;
    }

    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
        printHistory(id);
    }

}
