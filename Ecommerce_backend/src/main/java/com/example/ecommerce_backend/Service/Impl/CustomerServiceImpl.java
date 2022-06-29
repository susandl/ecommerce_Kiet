package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Exception.CustomerNotFound;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.dto.request.CustomerRequestDto;
import com.example.ecommerce_backend.dto.response.CustomerResponseDto;
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

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customers =  customerRepository.findAll();
        if (customers.isEmpty())
            throw new CustomerNotFound();
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent())
            return customerOptional.get();
        else
            throw new CustomerNotFound(id);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomerDto() {
        List<Customer> customers = customerRepository.findAll();
        if (!customers.isEmpty()){
        Type listType = new TypeToken<List<CustomerResponseDto>>() {
        }.getType();
        List<CustomerResponseDto> customersDto = modelMapper.map(customers, listType);
        customersDto.forEach(customerResponseDto -> System.out.println(customerResponseDto.getName()));
        return customersDto;
        }
        throw new CustomerNotFound();
    }

    @Override
    public void createCustomer(CustomerRequestDto customerDto) {
        Customer customer= modelMapper.map(customerDto,Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }
}
