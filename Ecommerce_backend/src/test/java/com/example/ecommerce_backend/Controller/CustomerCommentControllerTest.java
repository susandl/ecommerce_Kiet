package com.example.ecommerce_backend.Controller;

import com.example.ecommerce_backend.Data.Entity.CommentId;
import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Data.Repo.CommentRepository;
import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Request.CommentRequestDto;
import com.example.ecommerce_backend.Dto.Response.CommentResponseDto;
import com.example.ecommerce_backend.EcommerceBackendApplication;
import com.example.ecommerce_backend.Service.CategoryService;
import com.example.ecommerce_backend.Service.CommentService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EcommerceBackendApplication.class)
public class CustomerCommentControllerTest {
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private CommentService commentService;
    @MockBean
    private CommentRepository commentRepository;
    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
//                .apply(springSecurity())
                .build();
    }

    @Test
    void getCommentsByProductId_ShouldReturnCommentList_WhenAPICallCorrect() throws Exception {
        CommentId commentId = new CommentId(1L,1L);
        CommentResponseDto commentResponseDto = new CommentResponseDto(commentId,"details");
        List<CommentResponseDto> list = Arrays.asList(commentResponseDto);
        when(commentService.getCommentsByProductId(1L)).thenReturn(list);
        mockMvc.perform(get("/customer/comment/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].details").value("details"));
    }
    @Test
    void createComment_ShouldReturnOK_WhenAPICallCorrect() throws Exception {
        CommentRequestDto commentRequestDto = new CommentRequestDto(1L,"details");
        mockMvc.perform(post("/customer/comment/create/{id}",1L)
                        .content(asJsonString(commentRequestDto))
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
