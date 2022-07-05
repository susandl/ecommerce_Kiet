package com.example.ecommerce_backend.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    @NotEmpty(message = "username must not be empty")
    private String username;
    @NotEmpty(message = "password must not be empty")
    private String password;
    @NotEmpty(message = "role must not be empty")
    private String role;
}
