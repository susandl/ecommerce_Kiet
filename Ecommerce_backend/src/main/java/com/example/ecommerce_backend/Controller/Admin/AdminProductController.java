package com.example.ecommerce_backend.Controller.Admin;

import com.example.ecommerce_backend.Service.ProductService;
import com.example.ecommerce_backend.Dto.Request.ProductRequestDto;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin/product")
public class AdminProductController {
    private final ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductResponseDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id) {
        return this.productService.getProductById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody ProductRequestDto dto) {
        this.productService.createProduct(dto);
    }

    @PutMapping("/update/{id}")
    public void modifyProduct(@Valid @RequestBody ProductRequestDto dto,@PathVariable("id") Long id){
        this.productService.modifyProduct(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductById(id);
    }

}
