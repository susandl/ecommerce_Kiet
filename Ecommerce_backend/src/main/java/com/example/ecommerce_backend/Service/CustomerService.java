package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDto> getAllCustomer();

    CustomerResponseDto getCustomerByName(String name);

    String createCustomer(SignupRequestDto customer);

    String deleteCustomerByName(String name);

    String modifyCustomer(Long id, CustomerRequestDto customerRequestDto);
}
