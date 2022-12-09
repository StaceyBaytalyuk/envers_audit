package org.example.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public record IdResponse(
        @NotNull
        @JsonProperty("id")
        UUID id
) {
}
