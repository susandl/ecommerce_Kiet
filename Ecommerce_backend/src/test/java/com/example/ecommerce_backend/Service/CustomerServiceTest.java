package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Role;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Data.Repo.RoleRepository;
import com.example.ecommerce_backend.Dto.Request.CustomerRequestDto;
import com.example.ecommerce_backend.Dto.Request.SignupRequestDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ResourceNotFound;
import com.example.ecommerce_backend.Service.Impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    CustomerServiceImpl customerService;

    @BeforeEach
    void beforeEach(){
    }
    @Test
    void getCustomer_ShouldReturnCustomerResponseDto_WhenNameIsCorrect(){
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        Customer customer = new Customer();
        when(customerRepository.findByName("Kiet")).thenReturn(customer);
        when(modelMapper.map(customer,CustomerResponseDto.class)).thenReturn(customerResponseDto);
        CustomerResponseDto result = customerService.getCustomerByName("Kiet");
        verify(customerRepository).findByName("Kiet");
        verify(modelMapper).map(customer,CustomerResponseDto.class);
        assertThat(result,is(customerResponseDto));
    }

    @Test
    void getCustomer_ShouldReturnCustomerNotFound_WhenNameIsIncorrect(){
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.getCustomerByName("Wibu"));
        assertThat(e.getMessage(), is("Customer Wibu not found"));
    }

    @Test
    void getAllCustomer_ShouldReturnCustomerResponseList_WhenCustomerExist(){
        CustomerResponseDto customer = new CustomerResponseDto();
        Customer customer1 = new Customer();
        List<CustomerResponseDto> customers = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        customers.add(customer);
        customerList.add(customer1);
        Type listType = new TypeToken<List<CustomerResponseDto>>() {
        }.getType();
        when(customerRepository.findAll()).thenReturn(customerList);
        when(modelMapper.map(customerList,listType)).thenReturn(customers);
        List<CustomerResponseDto> result = customerService.getAllCustomer();
        assertThat(result,is(customers));
    }

    @Test
    void getAllCustomer_ShouldReturnCustomerNotFound_WhenCustomerDoesNotExist(){
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.getAllCustomer());
        assertThat(e.getMessage(),is("Customer List not found"));
    }

    @Test
    void createCustomer_ReturnCustomerException_WhenCustomerNameExists(){
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("Wibu");
        when(customerRepository.existsByName("Wibu")).thenReturn(true);
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.createCustomer(signupRequestDto));
        assertThat(e.getMessage(),is("Customer Wibu exists"));
    }

    @Test
    void createCustomer_ReturnRoleNotFoundException_WhenRoleDoesNotExist(){
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setRole("Wibu");
        when(roleRepository.existsByName("Wibu")).thenReturn(false);
        ResourceNotFound e = Assertions.assertThrows(ResourceNotFound.class,
                () -> customerService.createCustomer(signupRequestDto));
        assertThat(e.getMessage(),is("Role name not found"));
    }

    @Test
    void createCustomer_ReturnSuccess_WhenNameDoesNotExistAndRoleExist(){
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("Wibu");
        signupRequestDto.setRole("Wibu");
        when(customerRepository.existsByName("Wibu")).thenReturn(false);
        when(roleRepository.existsByName("Wibu")).thenReturn(true);
        String result = customerService.createCustomer(signupRequestDto);
        assertThat(result,is("Created"));
    }

    @Test
    void deleteCustomer_ReturnSuccess_WhenCustomerNameExist(){
        when(customerRepository.existsByName("Wibu")).thenReturn(true);
        customerService.deleteCustomerByName("Wibu");
        verify(customerRepository,times(1)).deleteCustomerByName("Wibu");
    }

    @Test
    void deleteCustomer_ReturnException_WhenCustomerNameDoesNotExist(){
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.deleteCustomerByName("Wibu"));
        assertThat(e.getMessage(),is("Customer Wibu does not exist"));
    }

    @Test
    void modifyCustomer_ReturnSuccess_WhenCustomerIdExist(){
        Optional<Customer> customer = Optional.of(new Customer());
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        when(customerRepository.findById(1L)).thenReturn(customer);
        String result = customerService.modifyCustomer(1L,customerRequestDto);
        assertThat(result,is("Modified"));
    }

    @Test
    void modifyCustomer_ReturnException_WhenCustomerIdDoesNotExist(){
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.modifyCustomer(1L,customerRequestDto));
        assertThat(e.getMessage(), is("Customer not found"));
    }
}
