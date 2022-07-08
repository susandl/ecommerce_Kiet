package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import com.example.ecommerce_backend.Exception.CustomerNotFound;
import com.example.ecommerce_backend.Service.Impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    CustomerServiceImpl customerService;

    @BeforeEach
    void beforeEach(){
    }
    @Test
    void getCustomer_ShouldReturnCustomerResponseDto_WhenNameIsCorrect(){
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setName("Kiet");
        Customer customer = new Customer();
        when(customerRepository.findByName("Kiet")).thenReturn(customer);
        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);
        CustomerResponseDto result = customerService.getCustomerByName("Kiet");
        assertThat(result,is(customerResponseDto));
    }
    @Test
    void getCustomer_ShouldReturnCustomerNotFound_WhenNameIsIncorrect(){
        CustomerNotFound e = Assertions.assertThrows(CustomerNotFound.class,
                () -> customerService.getCustomerByName("Goku"));
        assertThat(e.getMessage(), is("Customer Goku not found"));

    }


}
