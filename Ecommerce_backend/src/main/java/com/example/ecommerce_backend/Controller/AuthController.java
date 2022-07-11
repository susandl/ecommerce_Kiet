package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Service.AuthService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Login success"),
            @ApiResponse(responseCode = "401", description = "Login failed")})
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(authService.signinUser(customerRequestDto));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Register success"),
            @ApiResponse(responseCode = "400", description = "Invalid value")})
    public void registerUser(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        authService.registerUser(customerRequestDto);
    }

}
