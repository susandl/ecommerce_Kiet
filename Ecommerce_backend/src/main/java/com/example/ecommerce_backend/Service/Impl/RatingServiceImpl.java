package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Entity.Rating;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Data.Repo.RatingRepository;
import com.example.ecommerce_backend.Dto.Request.RatingRequestDto;
import com.example.ecommerce_backend.Dto.Response.RatingResponseDto;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ProductNotFound;
import com.example.ecommerce_backend.Exception.RatingNotFound;
import com.example.ecommerce_backend.Service.RatingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<RatingResponseDto> getRatingsByProductId(Long id) {
        List<Rating> ratings = this.ratingRepository.findAllByProductId(id);
        if (ratings.isEmpty()) {
            throw new RatingNotFound();
        }
        Type listType = new TypeToken<List<RatingResponseDto>>() {
        }.getType();
        List<RatingResponseDto> ratingResponseDtos = modelMapper.map(ratings, listType);
        return ratingResponseDtos;
    }

    @Override
    public List<RatingResponseDto> getRatingsByProductName(String name) {
        List<Rating> ratings = this.ratingRepository.findAllByProductName(name);
        if (ratings.isEmpty()) {
            throw new RatingNotFound(name);
        }
        Type listType = new TypeToken<List<RatingResponseDto>>() {
        }.getType();
        List<RatingResponseDto> ratingResponseDtos = modelMapper.map(ratings, listType);
        return ratingResponseDtos;
    }

    @Override
    public void createRating(RatingRequestDto ratingRequestDto) {
        Optional<Product> product = this.productRepository.findById(ratingRequestDto.getProductId());
        Optional<Customer> customer = this.customerRepository.findById(ratingRequestDto.getCustomerId());
        if (product.isEmpty()) {
            throw new ProductNotFound(ratingRequestDto.getProductId());
        }
        if (customer.isEmpty()) {
            throw new CustomerException(ratingRequestDto.getCustomerId());
        }
        Rating rating = new Rating(customer.get(), product.get(), ratingRequestDto.getPoint());
        this.ratingRepository.save(rating);
    }
}
