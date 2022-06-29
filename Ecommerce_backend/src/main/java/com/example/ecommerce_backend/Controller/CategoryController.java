package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategory(){
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getCategoryById(@PathVariable("id") Long id){
        return this.categoryService.getCategoryById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createCategory(@RequestBody Category category){
        this.categoryService.createCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable("id") Long id){
        this.categoryService.deleteCategoryById(id);
    }

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
}
