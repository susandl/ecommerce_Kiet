package com.example.ecommerce_backend.Controller.Admin;

import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponseDto> getAllCategory() {
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable("id") Long id) {
        return this.categoryService.getCategoryById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        this.categoryService.createCategory(categoryRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryById(@PathVariable("id") Long id) {
        this.categoryService.deleteCategoryById(id);
    }


}