package com.example.ecommerce_backend.Exception;

public class RatingNotFound extends RuntimeException {
    public RatingNotFound() {
        super("Rating list not found");
    }

    public RatingNotFound(String name) {
        super("Rating list for product " + name + " not found");
    }
}
