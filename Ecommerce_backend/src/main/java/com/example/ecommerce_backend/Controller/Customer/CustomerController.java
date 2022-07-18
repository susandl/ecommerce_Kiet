package com.example.ecommerce_backend.Controller.Customer;

import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import com.example.ecommerce_backend.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{name}")
    public CustomerResponseDto getCustomerByName(@PathVariable String name) {
        return customerService.getCustomerByName(name);
    }
}
