package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Dto.Response.CustomerResponseDto;
import com.example.ecommerce_backend.EcommerceBackendApplication;
import com.example.ecommerce_backend.Service.CategoryService;
import com.example.ecommerce_backend.Service.CustomerService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EcommerceBackendApplication.class)
public class AdminCategoryControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CategoryService categoryService;
    @MockBean
    private CategoryRepository categoryRepository;


    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
//                .apply(springSecurity())
                .build();
    }
    @Test
    void getAllCategory_ShouldReturnCategoryList_WhenAPICallCorrect() throws Exception {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName("Cat");
        List<CategoryResponseDto> categoryResponseDtos = Arrays.asList(categoryResponseDto);
        when(categoryService.getAllCategories()).thenReturn(categoryResponseDtos);
        mockMvc.perform(get("/admin/category")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Cat"));
    }

    @Test
    void getCategoryById_ShouldReturnCategory_WhenIdExist() throws Exception {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(1L);
        when(categoryService.getCategoryById(1L)).thenReturn(categoryResponseDto);
        mockMvc.perform(get("/admin/category/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void createCategory_ShouldReturnCreated_WhenRequestCorrect() throws Exception{
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto("abc","abc");
        mockMvc.perform(post("/admin/category/create")
                        .content(asJsonString(categoryRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void updateCategory_ShouldReturnOK_WhenRequestCorrect() throws Exception{
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto("abc","abc");
        mockMvc.perform(put("/admin/category/update/{id}",10L)
                        .content(asJsonString(categoryRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteCategory_ShouldReturnOK_WhenIdCorrect() throws Exception {
        mockMvc.perform(delete("/admin/category/delete/{id}",10L))
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
