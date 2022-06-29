package com.example.ecommerce_backend.Exception;

import com.example.ecommerce_backend.Controller.CustomerController;

public class CustomerNotFound extends RuntimeException{

    public CustomerNotFound(Long id){
        super("Customer id: "+id+" not found");
    }
    public CustomerNotFound(){
        super("Customer list not found");
    }
}
