package com.example.ecommerce_backend.dto.response;

import com.example.ecommerce_backend.Data.Entity.Comment;
import com.example.ecommerce_backend.Data.Entity.Rating;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class CustomerResponseDto {
    @JsonProperty("logging name: ")
    private String name;
}
