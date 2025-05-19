package com.example.oriontek.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCreateAddress (@NotBlank String street, @NotBlank String city,
                                @NotBlank String state, String zipCode, @NotNull Long idCustomer){
}
