package org.example.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCustomerRequest {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("age")
    private String age;

    @NotNull
    @JsonProperty("city")
    private String city;
}
