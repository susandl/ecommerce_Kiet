package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Exception.ProductNotFound;
import com.example.ecommerce_backend.Service.ProductService;
import com.example.ecommerce_backend.dto.request.ProductRequestDto;
import com.example.ecommerce_backend.dto.response.ProductResponseDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public List<Product> getProductsByCategoryName(String name) {
        return productRepository.findAllProductByCategoryName(name);
    }

    @Override
    public ProductResponseDto getProductDtoByName(String name) {
        Optional<Product> p = Optional.ofNullable(productRepository.getProductByName(name));
        if(p.isPresent()) {
            System.out.println("Product dto\n"+p.toString()+"\n\n");
            Product p1 = p.get();
            ProductResponseDto productResponseDto= modelMapper.map(p1, ProductResponseDto.class);
            return  productResponseDto;
        }
        else throw new ProductNotFound(name);
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto,Product.class);
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository,ModelMapper modelMapper){
        this.productRepository=productRepository;
        this.modelMapper=modelMapper;
        this.categoryRepository=categoryRepository;
    }

}
