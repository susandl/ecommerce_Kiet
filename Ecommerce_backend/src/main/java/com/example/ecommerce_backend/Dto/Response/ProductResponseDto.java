package com.example.ecommerce_backend.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String details;
    private Long price;
    private String imageUrl;
}
