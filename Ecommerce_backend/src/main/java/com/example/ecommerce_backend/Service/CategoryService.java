package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    void createCategory(CategoryRequestDto categoryRequestDto);

    void deleteCategoryById(Long id);

    void modifyCustomer(Long id, CategoryRequestDto categoryRequestDto);
}
