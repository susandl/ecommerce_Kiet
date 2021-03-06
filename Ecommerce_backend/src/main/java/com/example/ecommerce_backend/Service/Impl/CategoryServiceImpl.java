package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Exception.CategoryException;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new CategoryException("Category list not found");
        }
        Type listType = new TypeToken<List<CategoryResponseDto>>() {
        }.getType();
        List<CategoryResponseDto> categoryResponseDtos = modelMapper.map(categories, listType);
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new CategoryException("Category " + id + " not found");
        }
        Category category1 = category.get();
        CategoryResponseDto categoryResponseDto = modelMapper.map(category1, CategoryResponseDto.class);
        return categoryResponseDto;
    }

    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRepository.existsByName(categoryRequestDto.getName())) {
            throw new CategoryException("Category " + categoryRequestDto.getName() + " exist");
        }
        Category category = modelMapper.map(categoryRequestDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryException("category " + id + " does not exists");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void modifyCategory(Long id, CategoryRequestDto categoryRequestDto) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new CategoryException("Category not found");
        }
        Category result = category.get();
        modelMapper.map(categoryRequestDto,result);
        categoryRepository.save(result);
    }

}
