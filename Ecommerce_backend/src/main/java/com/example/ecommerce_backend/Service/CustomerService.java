package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDto> getAllCustomer();

    CustomerResponseDto getCustomerByName(String name);

     void createCustomer(SignupRequestDto customer);

    void deleteCustomerById(Long id);

     void modifyCustomer(Long id, CustomerRequestDto customerRequestDto);
}
