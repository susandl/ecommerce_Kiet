package com.example.ecommerce_backend.Exception;

public class ProductException extends RuntimeException {
    public ProductException(String message) {
        super(message);
    }

    public ProductException(Long id) {
        super("product id " + id + " not found");
    }
}
