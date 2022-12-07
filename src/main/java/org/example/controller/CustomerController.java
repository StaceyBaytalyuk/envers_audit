package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "customer/{id}/name/{name}")
    public void updateCustomer(@PathVariable("id") @NotNull UUID id,
                               @PathVariable("name") String name) {
        customerService.updateName(id, name);
    }

}
