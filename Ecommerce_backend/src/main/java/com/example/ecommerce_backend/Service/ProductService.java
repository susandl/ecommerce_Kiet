package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Dto.Request.ProductRequestDto;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> getProductsByCategoryName(String name);

    ProductResponseDto getProductByName(String name);

    void createProduct(ProductRequestDto productRequestDto);

    void deleteProductById(Long id);
}
