package com.example.ecommerce_backend.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    @NotEmpty(message = "Product name must be not empty")
    private String name;
    private String details;
    private Long price;
    private String  categoryName;

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", categoryId=" + categoryName +
                '}';
    }
}
