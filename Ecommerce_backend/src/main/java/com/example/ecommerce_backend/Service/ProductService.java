package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.dto.request.ProductRequestDto;
import com.example.ecommerce_backend.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProductByName(String name);
    List<Product> getProductsByCategoryName(String name);
    ProductResponseDto getProductDtoByName(String name);
    void createProduct(ProductRequestDto productRequestDto);
    void deleteProductById(Long id);
}
