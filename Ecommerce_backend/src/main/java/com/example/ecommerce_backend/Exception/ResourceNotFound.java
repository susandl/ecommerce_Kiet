package com.example.ecommerce_backend.Exception;

import java.util.function.Supplier;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
