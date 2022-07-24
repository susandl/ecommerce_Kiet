package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Exception.CategoryException;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ProductException;
import com.example.ecommerce_backend.Service.ProductService;
import com.example.ecommerce_backend.Dto.Request.ProductRequestDto;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryName(String name) {
        List<Product> products = productRepository.findAllProductByCategoryName(name);
        if (products.isEmpty()) {
            throw new ProductException("Product List not found");
        }
        Type listType = new TypeToken<List<ProductResponseDto>>() {
        }.getType();
        List<ProductResponseDto> productResponseDtos = modelMapper.map(products, listType);
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException("Product id "+id +" not found");
        }
        ProductResponseDto productResponseDto = modelMapper.map(product.get(), ProductResponseDto.class);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty())
            throw new ProductException("Product List not found");
        Type listType = new TypeToken<List<ProductResponseDto>>() {
        }.getType();
        List<ProductResponseDto> productResponseDtos = modelMapper.map(products, listType);
        return productResponseDtos;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        if (productRepository.existsByName(productRequestDto.getName())) {
            throw new ProductException("Product " + productRequestDto.getName() + " exists");
        }
        if (!categoryRepository.existsByName(productRequestDto.getCategoryName())) {
            throw new CategoryException("Category " + productRequestDto.getCategoryName() + " not found");
        }
        Product product = new Product();
        modelMapper.map(productRequestDto,product);
        Category category = categoryRepository.findByName(productRequestDto.getCategoryName());
        product.setCategory(category);
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(product.getCreatedDate());
        productRepository.save(product);

    }

    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductException("product id " + id + " does not exists");
        }
        productRepository.deleteById(id);
    }

    @Override
    public void modifyProduct(Long id, ProductRequestDto dto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException("Product not found");
        }
        if (!categoryRepository.existsByName(dto.getCategoryName())) {
            throw new CategoryException("Category " + dto.getCategoryName() + " not found");
        }
        Category category = categoryRepository.findByName(dto.getCategoryName());
        Product result = product.get();
        modelMapper.map(dto,result);
        result.setCategory(category);
        result.setUpdatedDate(LocalDateTime.now());
        productRepository.save(result);
    }


}
