package com.example.ecommerce_backend.dto.request;

import com.example.ecommerce_backend.Data.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    @NotEmpty(message = "Product name must be not empty")
    private String name;
    private String details;
    @NotNull
    private String categoryName;

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", categoryName=" + categoryName +
                '}';
    }
}
