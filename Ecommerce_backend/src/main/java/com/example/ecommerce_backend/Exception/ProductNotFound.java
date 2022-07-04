package com.example.ecommerce_backend.Exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message) {
        super(message);
    }

    public ProductNotFound(Long id) {
        super("product id " + id + " not found");
    }
}
