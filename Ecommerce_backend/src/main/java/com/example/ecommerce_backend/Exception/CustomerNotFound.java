package com.example.ecommerce_backend.Exception;

public class CustomerNotFound extends RuntimeException {

    public CustomerNotFound(Long id) {
        super("Customer id: " + id + " not found");
    }

    public CustomerNotFound(String message) {
        super(message);
    }

}
