package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    void createCategory(CategoryRequestDto categoryRequestDto);

    void deleteCategoryById(Long id);

    void modifyCategory(Long id, CategoryRequestDto categoryRequestDto);
}
