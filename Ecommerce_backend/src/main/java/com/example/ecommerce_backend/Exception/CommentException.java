package com.example.ecommerce_backend.Exception;

public class CommentException extends RuntimeException {
    public CommentException() {
        super("Comment list not found");
    }

    public CommentException(String name) {
        super(name);
    }
}
