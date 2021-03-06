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
import org.mockito.ArgumentCaptor;
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
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    CustomerServiceImpl customerService;
    CustomerResponseDto customerResponseDto;
    Customer customer;
    CustomerRequestDto customerRequestDto;
    @BeforeEach
    void beforeEach(){
        customerResponseDto = mock(CustomerResponseDto.class);
        customer = mock(Customer.class);
        customerRequestDto = mock(CustomerRequestDto.class);
    }
    @Test
    void getCustomer_ShouldReturnCustomerResponseDto_WhenNameIsCorrect(){
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
        List<CustomerResponseDto> customerDtoList = mock(ArrayList.class);
        List<Customer> customerList = mock(ArrayList.class);
        Type listType = new TypeToken<List<CustomerResponseDto>>() {
        }.getType();
        when(customerRepository.findAll()).thenReturn(customerList);
        when(modelMapper.map(customerList,listType)).thenReturn(customerDtoList);
        List<CustomerResponseDto> result = customerService.getAllCustomer();
        assertThat(result,is(customerDtoList));
    }

    @Test
    void getAllCustomer_ShouldReturnCustomerNotFound_WhenCustomerDoesNotExist(){
        List<Customer> customerList = mock(ArrayList.class);
        when(customerRepository.findAll()).thenReturn(customerList);
        when(customerList.isEmpty()).thenReturn(true);
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.getAllCustomer());
        assertThat(e.getMessage(),is("Customer List not found"));
    }

    @Test
    void createCustomer_ReturnCustomerException_WhenCustomerNameExists(){
        SignupRequestDto signupRequestDto = mock(SignupRequestDto.class);
        when(customerRepository.existsByName(signupRequestDto.getUsername())).thenReturn(true);
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.createCustomer(signupRequestDto));
        assertThat(e.getMessage(),is("Customer "+signupRequestDto.getUsername()+" exists"));
    }

    @Test
    void createCustomer_ReturnRoleNotFoundException_WhenRoleDoesNotExist(){
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        when(roleRepository.existsByName(signupRequestDto.getUsername())).thenReturn(false);
        ResourceNotFound e = Assertions.assertThrows(ResourceNotFound.class,
                () -> customerService.createCustomer(signupRequestDto));
        assertThat(e.getMessage(),is("Role name not found"));
    }

    @Test
    void createCustomer_ShouldSuccess_WhenNameDoesNotExistAndRoleExist(){
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("Wibu");
        signupRequestDto.setPassword("Wibu");
        signupRequestDto.setRole("Wibu");
        when(customerRepository.existsByName("Wibu")).thenReturn(false);
        when(roleRepository.existsByName("Wibu")).thenReturn(true);
        customerService.createCustomer(signupRequestDto);
        ArgumentCaptor<Customer>captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());
        Customer customer1 = captor.getValue();
        assertThat(customer1.getName(),is("Wibu"));
        assertThat(customer1.getPass(),is("Wibu"));
        assertThat(customer1.getRole().size(),is(1));
//        List<String> role = customer1.getRole().stream().map(Role::getName).toList();
//        assertThat(role.get(0),is("Wibu"));
    }

    @Test
    void deleteCustomer_ReturnSuccess_WhenCustomerNameExist(){
        when(customerRepository.existsById(1L)).thenReturn(true);
        customerService.deleteCustomerById(1L);
        verify(customerRepository,times(1)).deleteCustomerByName("Wibu");
    }

    @Test
    void deleteCustomer_ReturnException_WhenCustomerNameDoesNotExist(){
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.deleteCustomerById(1L));
        assertThat(e.getMessage(),is("Customer id 1 does not exist"));
    }

    @Test
    void modifyCustomer_ReturnSuccess_WhenCustomerIdExist(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.modifyCustomer(1L,customerRequestDto);
        verify(modelMapper).map(customerRequestDto,customer);
        verify(customerRepository).save(customer);
    }

    @Test
    void modifyCustomer_ReturnException_WhenCustomerIdDoesNotExist(){
        when(customerRepository.findById(1L)).thenReturn(null);
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> customerService.modifyCustomer(1L,customerRequestDto));
        assertThat(e.getMessage(), is("Customer not found"));
    }
}
