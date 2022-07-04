package com.example.ecommerce_backend.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    @JsonProperty("Product name")
    private String name;
    @JsonProperty("Product details")
    private String details;

}
