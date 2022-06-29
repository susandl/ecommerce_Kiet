package com.example.ecommerce_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    @NotEmpty(message = "Customer name must be not empty")
    private String name;
    @NotEmpty(message = "Customer password must be not empty")
    private String pass;
}
