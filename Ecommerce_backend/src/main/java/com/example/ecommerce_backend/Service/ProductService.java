package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Dto.Request.ProductRequestDto;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> getProductsByCategoryId(Long id);

    ProductResponseDto getProductById(Long id);

    void createProduct(ProductRequestDto productRequestDto);

    void deleteProductById(Long id);

    void modifyProduct(Long id, ProductRequestDto dto);
}
