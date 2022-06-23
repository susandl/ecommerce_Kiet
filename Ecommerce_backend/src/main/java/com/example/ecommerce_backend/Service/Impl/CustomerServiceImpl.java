package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        System.out.println("call customer service");
        return customerRepository.findAll();
    }
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
}
