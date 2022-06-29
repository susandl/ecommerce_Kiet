package com.example.ecommerce_backend.Exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String name){
        super("product name " + name +" not found");
    }
}
