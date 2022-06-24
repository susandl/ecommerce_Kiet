package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Entity.Rating;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.Service.ProductService;
import com.example.ecommerce_backend.Service.RatingService;
import com.example.ecommerce_backend.dto.response.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CustomerService customerService;
    private final ProductService productService;
    private final RatingService ratingService;
    @GetMapping("")
    public String home(){
        return "Home page";
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @GetMapping("/customerdto")
    public List<CustomerResponseDto> getCustomersDto(){
        System.out.println("call customerService");
        return customerService.getAllCustomerDto();
    }
    @GetMapping("/product")
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/rating")
    public List<Rating> getRatings(){
        return ratingService.getRatings();
    }
    @Autowired
    public HomeController(CustomerService customerService,ProductService productService,RatingService ratingService){
        this.productService=productService;
        this.customerService=customerService;
        this.ratingService=ratingService;
    }
}