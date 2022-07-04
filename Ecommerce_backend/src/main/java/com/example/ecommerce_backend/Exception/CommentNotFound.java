package com.example.ecommerce_backend.Exception;

public class CommentNotFound extends RuntimeException {
    public CommentNotFound() {
        super("Comment list not found");
    }

    public CommentNotFound(String name) {
        super("Comment list for product " + name + " not found");
    }
}
