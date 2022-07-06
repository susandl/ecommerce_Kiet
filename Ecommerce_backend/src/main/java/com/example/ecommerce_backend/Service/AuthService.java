package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Dto.Response.JwtResponse;

public interface AuthService {
    void registerUser(CustomerRequestDto customerRequestDto);

    JwtResponse signinUser(CustomerRequestDto customerRequestDto);
}
