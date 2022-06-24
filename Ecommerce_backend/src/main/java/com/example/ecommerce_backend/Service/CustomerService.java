package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();
    List<CustomerResponseDto> getAllCustomerDto();
}