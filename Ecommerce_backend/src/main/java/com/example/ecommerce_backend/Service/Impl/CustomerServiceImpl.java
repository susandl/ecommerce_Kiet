package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Exception.CustomerNotFound;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty())
            throw new CustomerNotFound("Customer List not found");
        Type listType = new TypeToken<List<CustomerResponseDto>>() {
        }.getType();
        List<CustomerResponseDto> customerResponseDtos = modelMapper.map(customers, listType);
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto getCustomerByName(String name) {
        Optional<Customer> customerOptional = Optional.ofNullable(customerRepository.findByName(name));
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFound("Customer " + name + " not found");
        }
        CustomerResponseDto customerResponseDto = this.modelMapper.map(customerOptional.get(), CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomerByNameAndPass(String name, String pass) {
        Optional<Customer> customerOptional = Optional.ofNullable(customerRepository.findByNameAndPass(name, pass));
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFound("Customer " + name + " not found");
        }
        CustomerResponseDto customerResponseDto = this.modelMapper.map(customerOptional.get(), CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public void createCustomer(SignupRequestDto signupRequestDto) {
        if (customerRepository.existsByName(signupRequestDto.getUsername())) {
            throw new CustomerNotFound("customer " + signupRequestDto.getUsername() + " exists");
        }
        Customer customer = modelMapper.map(signupRequestDto, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerByName(String name) {
        if (!customerRepository.existsByName(name)) {
            throw new CustomerNotFound("customer " + name + " does not exists");
        }
        customerRepository.deleteCustomerByName(name);
    }

}