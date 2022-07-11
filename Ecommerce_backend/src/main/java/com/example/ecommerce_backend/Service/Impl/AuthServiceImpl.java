package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Role;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Data.Repo.RoleRepository;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Response.JwtResponse;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Security.Jwt.JwtUtil;
import com.example.ecommerce_backend.Security.Service.UserDetailsImpl;
import com.example.ecommerce_backend.Service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    final private PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    final private AuthenticationManager authenticationManager;

    @Autowired
    AuthServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper,
                    PasswordEncoder encoder, RoleRepository roleRepository,
                    AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void registerUser(CustomerRequestDto customerRequestDto) {
        if (customerRepository.existsByName(customerRequestDto.getName())) {
            throw new CustomerException("User name already exists");
        }
        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("CUSTOMER");
        roles.add(role);
        customer.setRole(roles);
        System.out.println(customer.toString());
        customerRepository.save(customer);
    }

    @Override
    public JwtResponse signinUser(CustomerRequestDto customerRequestDto) {
        if (!customerRepository.existsByName(customerRequestDto.getName())) {
            throw new CustomerException("Customer " + customerRequestDto.getName() + " not found");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerRequestDto.getName(), customerRequestDto.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt, userDetails.getUsername(), roles);
    }
}
