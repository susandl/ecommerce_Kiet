package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Request.ProductRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;
import com.example.ecommerce_backend.EcommerceBackendApplication;
import com.example.ecommerce_backend.Service.CategoryService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EcommerceBackendApplication.class)
public class AdminProductControllerTest {
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
    void getAllProduct_ShouldReturnProductList_WhenAPICallCorrect() throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName("Pro");
        List<ProductResponseDto> productResponseDtos = Arrays.asList(productResponseDto);
        when(productService.getAllProducts()).thenReturn(productResponseDtos);
        mockMvc.perform(get("/admin/product")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Pro"));
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenIdCorrect() throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName("Pro");
        when(productService.getProductById(1L)).thenReturn(productResponseDto);
        mockMvc.perform(get("/admin/product/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Pro"));
    }

    @Test
    void createProduct_ShouldReturnCreate_WhenRequestCorrect() throws Exception {
        ProductRequestDto productRequestDto = new ProductRequestDto("abc","abc",200L,"abc");
        mockMvc.perform(post("/admin/product/create")
                        .content(asJsonString(productRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void updateProduct_ShouldReturnOK_WhenRequestCorrect() throws Exception {
        ProductRequestDto productRequestDto = new ProductRequestDto("abc","abc",200L,"abc");
        mockMvc.perform(put("/admin/product/update/{id}",10L)
                        .content(asJsonString(productRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteProduct_ShouldReturnOK_WhenAPICallCorrect() throws Exception {
        mockMvc.perform(delete("/admin/product/delete/{id}",10L))
                .andExpect(status().isOk())
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
