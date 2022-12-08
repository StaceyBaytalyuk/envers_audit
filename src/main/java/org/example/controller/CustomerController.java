package org.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.controller.model.CreateCustomerRequest;
import org.example.controller.model.IdResponse;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public IdResponse addCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        return new IdResponse(customerService.createCustomer(request));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void updateName(@PathVariable("id") @NotNull UUID id) {
        customerService.deleteCustomer(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}/name/{name}")
    public void updateName(@PathVariable("id") @NotNull UUID id,
                           @PathVariable("name") String name) {
        customerService.updateName(id, name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}/city/{city}")
    public void updateCity(@PathVariable("id") @NotNull UUID id,
                           @PathVariable("city") String city) {
        customerService.updateCity(id, city);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}/age/{age}")
    public void updateAge(@PathVariable("id") @NotNull UUID id,
                          @PathVariable("age") String age) {
        customerService.updateAge(id, age);
    }

}
