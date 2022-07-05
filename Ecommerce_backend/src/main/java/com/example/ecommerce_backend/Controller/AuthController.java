package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Data.Repo.RoleRepository;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Dto.Response.JwtResponse;
import com.example.ecommerce_backend.Security.Jwt.JwtUtil;
import com.example.ecommerce_backend.Security.Service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final CustomerRepository customerRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager
            , CustomerRepository customerRepository
            , RoleRepository roleRepository
            , PasswordEncoder passwordEncoder
            , JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.encoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerRequestDto.getName(), customerRequestDto.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        //TODO
        return null;
    }
}
