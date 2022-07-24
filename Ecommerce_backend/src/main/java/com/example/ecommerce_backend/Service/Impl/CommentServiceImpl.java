package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Comment;
import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Repo.CommentRepository;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Dto.Request.CommentRequestDto;
import com.example.ecommerce_backend.Dto.Response.CommentResponseDto;
import com.example.ecommerce_backend.Exception.CommentException;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ProductException;
import com.example.ecommerce_backend.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CommentResponseDto> getCommentsByProductId(Long id) {
        List<Comment> comments = this.commentRepository.findAllByProductId(id);
        if (comments.isEmpty()) {
            throw new CommentException();
        }
        Type listType = new TypeToken<List<CommentResponseDto>>() {
        }.getType();
        List<CommentResponseDto> commentResponseDtos = modelMapper.map(comments, listType);
        return commentResponseDtos;
    }

    @Override
    public List<CommentResponseDto> getCommentsByProductName(String name) {
        List<Comment> comments = this.commentRepository.findAllByProductName(name);
        if (comments.isEmpty()) {
            throw new CommentException("Comment list not found");
        }
        Type listType = new TypeToken<List<CommentResponseDto>>() {
        }.getType();
        List<CommentResponseDto> commentResponseDtos = modelMapper.map(comments, listType);
        return commentResponseDtos;
    }

    @Override
    public void createComment(CommentRequestDto commentRequestDto,Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        Optional<Customer> customer = this.customerRepository.findById(commentRequestDto.getCustomerId());
        if (product.isEmpty()) {
            throw new ProductException("Product id "+ id+ " not found");
        }
        if (customer.isEmpty()) {
            throw new CustomerException("Customer id "+ commentRequestDto.getCustomerId()+" not found");
        }
        Comment comment = new Comment(customer.get(), product.get(), commentRequestDto.getDetails());
        this.commentRepository.save(comment);
    }

}
