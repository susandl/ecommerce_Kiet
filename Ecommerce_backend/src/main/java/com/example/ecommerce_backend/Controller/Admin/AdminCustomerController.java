package com.example.ecommerce_backend.Controller.Admin;

import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin/customer")
public class AdminCustomerController {
    private final CustomerService customerService;

    @Autowired
    public AdminCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<CustomerResponseDto> getCustomersDto() {
        return customerService.getAllCustomer();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody SignupRequestDto dto) {
        this.customerService.createCustomer(dto);
    }

    @GetMapping("/{name}")
    public CustomerResponseDto getCustomerByName(@PathVariable("name") String name) {
        return customerService.getCustomerByName(name);
    }

    @PutMapping("/update/{id}")
    public void updateCustomer(@Valid @RequestBody CustomerRequestDto dto,@PathVariable("id") Long id){
        this.customerService.modifyCustomer(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerByName(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
    }


}