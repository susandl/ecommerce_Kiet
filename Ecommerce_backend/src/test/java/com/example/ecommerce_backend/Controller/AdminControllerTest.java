package com.example.ecommerce_backend.Controller;


import com.example.ecommerce_backend.Controller.Admin.AdminController;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import com.example.ecommerce_backend.EcommerceBackendApplication;
import com.example.ecommerce_backend.Security.Service.UserDetailsServiceImpl;
import com.example.ecommerce_backend.Service.CustomerService;
import com.example.ecommerce_backend.Service.Impl.CustomerServiceImpl;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EcommerceBackendApplication.class)
public class AdminControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CustomerService customerService;

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
        mockMvc.perform(get("/admin/{name}","Wibu")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Wibu"));

    }



}
