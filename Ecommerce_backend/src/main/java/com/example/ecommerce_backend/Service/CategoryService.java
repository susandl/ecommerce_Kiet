package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Customer;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void createCategory(Category category);
    void deleteCategoryById(Long id);
}
