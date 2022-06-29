package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Entity.Rating;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.Service.ProductService;
import com.example.ecommerce_backend.Service.RatingService;
import com.example.ecommerce_backend.dto.request.CustomerRequestDto;
import com.example.ecommerce_backend.dto.response.CustomerResponseDto;
import com.example.ecommerce_backend.dto.response.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/customerDto")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponseDto> getCustomersDto(){
        System.out.println("call customerService");
        return customerService.getAllCustomerDto();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody CustomerRequestDto dto){
        this.customerService.createCustomer(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable("id") Long id){
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable("id") Long id){
        customerService.deleteCustomerById(id);
    }

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
}