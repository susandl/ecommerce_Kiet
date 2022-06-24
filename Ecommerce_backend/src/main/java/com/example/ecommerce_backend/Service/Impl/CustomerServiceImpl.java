package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.dto.response.CustomerResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public List<Customer> getAllCustomer(){
        System.out.println("call customer service");
        return customerRepository.findAll();
    }

    @Override
    public List<CustomerResponseDto> getAllCustomerDto() {
        List<Customer> customers = customerRepository.findAll();
        Type listType = new TypeToken<List<CustomerResponseDto>>(){}.getType();
        List<CustomerResponseDto> customersDto = modelMapper.map(customers, listType);
        System.out.println("get customersdto");
        customersDto.forEach(customerResponseDto -> System.out.println(customerResponseDto.getName()));
        return customersDto;
    }

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.customerRepository=customerRepository;
    }
}
