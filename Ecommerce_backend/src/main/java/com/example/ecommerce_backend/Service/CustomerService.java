package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDto> getAllCustomer();

    CustomerResponseDto getCustomerByName(String name);

    CustomerResponseDto getCustomerByNameAndPass(String name, String pass);

    void createCustomer(CustomerRequestDto customer);

    void deleteCustomerByName(String name);
}
