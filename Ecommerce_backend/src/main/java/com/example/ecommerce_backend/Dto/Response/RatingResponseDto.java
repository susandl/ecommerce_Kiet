package com.example.ecommerce_backend.Dto.Response;

import com.example.ecommerce_backend.Data.Entity.RatingId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponseDto {
    private RatingId id;
    private float point;
}
