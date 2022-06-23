package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CustomerService customerService;

    @GetMapping("")
    public String home(){
        return "Home page";
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @Autowired
    public HomeController(CustomerService customerService){

        this.customerService=customerService;
    }
}