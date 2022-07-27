package com.example.ecommerce_backend.Dto.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String details;
    private Long price;
    private String imageUrl;
    @JsonFormat(pattern="yyyy-MM-dd hh-mm-ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern="yyyy-MM-dd hh-mm-ss")
    private LocalDateTime updatedDate;
}
