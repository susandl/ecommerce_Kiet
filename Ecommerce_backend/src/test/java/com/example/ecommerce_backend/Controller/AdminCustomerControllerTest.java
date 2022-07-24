package com.example.ecommerce_backend.Controller;


import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import com.example.ecommerce_backend.EcommerceBackendApplication;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ProductException;
import com.example.ecommerce_backend.Service.CustomerService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EcommerceBackendApplication.class)
public class AdminCustomerControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CustomerService customerService;
    @MockBean
    private CustomerRepository customerRepository;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
//                .apply(springSecurity())
                .build();
    }

    @Test
    public void getAllCustomer_ShouldReturnCustomerList_WhenApiCallCorrect () throws Exception{
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName("Kiet");
        List<CustomerResponseDto> customerResponseDtoList = Arrays.asList(customerResponseDto);
        when(customerService.getAllCustomer()).thenReturn(customerResponseDtoList);
        mockMvc.perform(get("/admin/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Kiet"));
    }

    @Test
    public void getCustomerByName_ShouldReturnCustomerDto_WhenNameIsExist () throws Exception{
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName("Wibu");
        when(customerService.getCustomerByName("Wibu")).thenReturn(customerResponseDto);
        mockMvc.perform(get("/admin/customer/{name}","Wibu")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Wibu"));

    }
    @Test
    public void deleteCustomer_ShouldReturnOK_WhenCustomerIdExist () throws Exception{
        mockMvc.perform(delete("/admin/customer/delete/{id}",10L))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void deleteCustomer_ShouldReturnNotFound_WhenCustomerIdDoesNotExist () throws Exception{
        Customer customer = new Customer();
        customer.setId(10L);
        doThrow(new CustomerException("Customer id "+customer.getId()+" not found"))
                .when(customerService).deleteCustomerById(customer.getId());
        mockMvc.perform(delete("/admin/customer/delete/{id}",customer.getId()))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateCustomer_ShouldReturnOK_WhenCustomerIdExist() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Kiet","Kiet");
        mockMvc.perform(put("/admin/customer/update/{id}",10L)
                        .content(asJsonString(customerRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
//    @Test
//    void updateCustomer_ShouldReturnNotFound_WhenCustomerIdNotExist() throws Exception {
//        CustomerRequestDto customerRequestDto = new CustomerRequestDto("Kiet","Kiet");
//        customerService.modifyCustomer(10L,customerRequestDto);
//        doThrow(new CustomerException("Customer id 10 not found"))
//                .when(customerService).modifyCustomer(10L,customerRequestDto);
//        mockMvc.perform(put("/admin/customer/update/{id}",15L)
//                        .content(asJsonString(customerRequestDto))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//    }

    @Test
    void createCustomer_ShouldReturnOK_WhenRequestDtoIsCorrect() throws Exception{
        SignupRequestDto signupRequestDto = new SignupRequestDto("Kiet","kiet","Kiet");
        mockMvc.perform(post("/admin/customer/create")
                        .content(asJsonString(signupRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
