package com.example.ecommerce_backend.dto.response;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Comment;
import com.example.ecommerce_backend.Data.Entity.Rating;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProductResponseDto {
    @JsonProperty("Product name")
    private String name;
    @JsonProperty("Product details")
    private String details;

}
