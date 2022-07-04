package com.example.ecommerce_backend.Exception;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(Long id) {
        super("Category id: " + id + " not found");
    }

    public CategoryNotFound() {
        super("Category list not found");
    }
}
