package org.example.converter;

import org.example.controller.model.CreateCustomerRequest;
import org.example.dao.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerConverter {
    public CustomerEntity convert(CreateCustomerRequest request) {
        return CustomerEntity.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .age(request.getAge())
                .city(request.getCity())
                .build();
    }
}
