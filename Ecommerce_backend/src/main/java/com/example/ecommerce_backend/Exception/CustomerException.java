package com.example.ecommerce_backend.Exception;

public class CustomerException extends RuntimeException {

    public CustomerException(Long id) {
        super("Customer id: " + id + " not found");
    }

    public CustomerException(String message) {
        super(message);
    }

}
