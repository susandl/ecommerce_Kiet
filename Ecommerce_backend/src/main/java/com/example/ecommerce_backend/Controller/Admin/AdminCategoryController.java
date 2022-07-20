package com.example.ecommerce_backend.Controller.Admin;

import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
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

    @PutMapping("/update/{id}")
    public void updateCustomer(@Valid @RequestBody CategoryRequestDto dto, @PathVariable("id") Long id){
        this.categoryService.modifyCategory(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryById(@PathVariable("id") Long id) {
        this.categoryService.deleteCategoryById(id);
    }


}
