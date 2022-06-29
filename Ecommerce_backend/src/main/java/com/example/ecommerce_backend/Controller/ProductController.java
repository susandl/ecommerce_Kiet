package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Service.ProductService;
import com.example.ecommerce_backend.dto.request.ProductRequestDto;
import com.example.ecommerce_backend.dto.response.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable("name")String name){
        return productService.getProductByName(name);
    }

    @GetMapping("/productdto/{name}")
    public ProductResponseDto getProductDtoByName(@PathVariable("name")String name) {
        return this.productService.getProductDtoByName(name);
    }

    @PostMapping("/create")
    public void createProduct(@Valid @RequestBody ProductRequestDto dto){
        this.productService.createProduct(dto);
    }

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
