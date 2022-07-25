package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Repo.CommentRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;
import com.example.ecommerce_backend.EcommerceBackendApplication;
import com.example.ecommerce_backend.Service.CommentService;
import com.example.ecommerce_backend.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EcommerceBackendApplication.class)
public class CustomerProductControllerTest {
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
//                .apply(springSecurity())
                .build();
    }

    @Test
    void getProducts_ShouldReturnProductList_WhenAPICallCorrect() throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto(1L,"product","detail",200L,"image.png");
        List<ProductResponseDto> productResponseDtoList = Arrays.asList(productResponseDto);
        when(productService.getAllProducts()).thenReturn(productResponseDtoList);
        mockMvc.perform(get("/customer/product")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].details").value("detail"))
                .andExpect(jsonPath("$[0].name").value("product"));
    }
    @Test
    void getProduct_ShouldReturnProduct_WhenAPICallCorrect() throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto(1L,"product","detail",200L,"image.png");
        when(productService.getProductById(1L)).thenReturn(productResponseDto);
        mockMvc.perform(get("/customer/product/{id}",1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("product"))
                .andExpect(jsonPath("$.details").value("detail"));
    }

    @Test
    void getProductByCategoryId_ShouldReturnProductList_WhenAPICallCorrect() throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto(1L,"product","detail",200L,"image.png");
        List<ProductResponseDto> productResponseDtoList = Arrays.asList(productResponseDto);
        when(productService.getProductsByCategoryId(1L)).thenReturn(productResponseDtoList);
        mockMvc.perform(get("/customer/product/category/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].details").value("detail"))
                .andExpect(jsonPath("$[0].name").value("product"));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
