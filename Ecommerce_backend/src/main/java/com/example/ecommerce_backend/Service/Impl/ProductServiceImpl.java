package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Exception.ProductNotFound;
import com.example.ecommerce_backend.Service.ProductService;
import com.example.ecommerce_backend.Dto.Request.ProductRequestDto;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryName(String name) {
        List<Product> products = productRepository.findAllProductByCategoryName(name);
        if (products.isEmpty()) {
            throw new ProductNotFound("Product List not found");
        }
        Type listType = new TypeToken<List<ProductResponseDto>>() {
        }.getType();
        List<ProductResponseDto> productResponseDtos = modelMapper.map(products, listType);
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto getProductByName(String name) {
        Optional<Product> product = Optional.ofNullable(productRepository.getProductByName(name));
        if (product.isEmpty()) {
            throw new ProductNotFound(name);
        }
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty())
            throw new ProductNotFound("Product List not found");
        Type listType = new TypeToken<List<ProductResponseDto>>() {
        }.getType();
        List<ProductResponseDto> productResponseDtos = modelMapper.map(products, listType);
        return productResponseDtos;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        if (productRepository.existsByName(productRequestDto.getName())) {
            throw new ProductNotFound("product " + productRequestDto.getName() + " exists");
        }
        Product product = modelMapper.map(productRequestDto, Product.class);
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFound("product id" + id + " does not exists");
        }
        productRepository.deleteById(id);
    }


}
