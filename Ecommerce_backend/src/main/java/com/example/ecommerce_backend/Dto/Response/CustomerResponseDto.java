package com.example.ecommerce_backend.Dto.Response;

import com.example.ecommerce_backend.Data.Entity.Comment;
import com.example.ecommerce_backend.Data.Entity.Rating;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String pass;

}
