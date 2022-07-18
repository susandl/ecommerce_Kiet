package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Role;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Data.Repo.RoleRepository;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ResourceNotFound;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        super();
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new CustomerException("Customer List not found");
        }
        Type listType = new TypeToken<List<CustomerResponseDto>>() {
        }.getType();
        List<CustomerResponseDto> customerResponseDtos = modelMapper.map(customers, listType);
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto getCustomerByName(String name) {
        Optional<Customer> customerOptional = Optional.ofNullable(customerRepository.findByName(name));
        if (customerOptional.isEmpty()) {
            throw new CustomerException("Customer " + name + " not found");
        }
        CustomerResponseDto customerResponseDto = this.modelMapper.map(customerOptional.get(), CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public void createCustomer(SignupRequestDto signupRequestDto) {
        if (customerRepository.existsByName(signupRequestDto.getUsername())) {
            throw new CustomerException("Customer " + signupRequestDto.getUsername() + " exists");
        }

        if (!roleRepository.existsByName(signupRequestDto.getRole())) {
            throw new ResourceNotFound("Role name not found");
        }
        Customer customer = new Customer();
        Role role = roleRepository.findByName(signupRequestDto.getRole());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        customer.setName(signupRequestDto.getUsername());
        customer.setPass(signupRequestDto.getPassword());
        customer.setRole(roles);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerException("Customer id " + id + " does not exist");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public void modifyCustomer(Long id, CustomerRequestDto customerRequestDto) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new CustomerException("Customer not found");
        }
        Customer customer = customerOptional.get();
        modelMapper.map(customerRequestDto,customer);
        customerRepository.save(customer);
    }

}