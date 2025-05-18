package com.example.oriontek.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCreateCustomer(@NotBlank String name,
                                @NotNull String email,
                                 String phone) {
}
