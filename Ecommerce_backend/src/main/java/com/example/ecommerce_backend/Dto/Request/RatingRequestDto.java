package com.example.ecommerce_backend.Dto.Request;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.RatingId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequestDto {
    private RatingId id;
    @NotNull
    private Long customerId;
    @NotNull
    private Long productId;
    @Min(value = 0)
    @Max(value = 5)
    private float point;

}
