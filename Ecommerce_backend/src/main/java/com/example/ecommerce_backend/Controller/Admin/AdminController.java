package com.example.ecommerce_backend.Controller.Admin;

import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final CustomerService customerService;

    @Autowired
    public AdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public List<CustomerResponseDto> getCustomersDto() {
        return customerService.getAllCustomer();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody CustomerRequestDto dto) {
        this.customerService.createCustomer(dto);
    }

    @GetMapping("/{name}")
    public CustomerResponseDto getCustomerByName(@PathVariable("name") String name) {
        return customerService.getCustomerByName(name);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteCustomerByName(@PathVariable("name") String name) {
        customerService.deleteCustomerByName(name);
    }


}