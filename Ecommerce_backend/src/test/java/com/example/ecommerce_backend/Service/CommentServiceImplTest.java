package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Comment;
import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Repo.CommentRepository;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Request.CommentRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Dto.Response.CommentResponseDto;
import com.example.ecommerce_backend.Exception.CategoryException;
import com.example.ecommerce_backend.Exception.CommentException;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ProductException;
import com.example.ecommerce_backend.Service.Impl.CommentServiceImpl;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    @Mock
    CommentRepository commentRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    CommentServiceImpl commentService;

    Comment comment;

    CommentRequestDto commentRequestDto;

    CommentResponseDto commentResponseDto;

    Product product;

    Customer customer;

    @BeforeEach
    void before(){
        comment = mock(Comment.class);
        commentRequestDto = mock(CommentRequestDto.class);
        commentResponseDto = mock(CommentResponseDto.class);
        product = mock(Product.class);
        customer = mock(Customer.class);
    }

    @Test
    void getCommentByProductId_ShouldReturnCommentResponseDto_WhenProductIdCorrect(){
        List<Comment> commentList = mock(ArrayList.class);
        List<CommentResponseDto> commentResponseDtoList = mock(ArrayList.class);
        when(commentRepository.findAllByProductId(1L)).thenReturn(commentList);
        when(commentList.isEmpty()).thenReturn(false);
        Type listType = new TypeToken<List<CommentResponseDto>>() {
        }.getType();
        when(modelMapper.map(commentList,listType)).thenReturn(commentResponseDtoList);
        List<CommentResponseDto> result = commentService.getCommentsByProductId(1L);
        assertThat(result,is(commentResponseDtoList));
    }
    @Test
    void getCommentByProductId_ShouldThrowsCommentException_WhenProductIdIsNotCorrect(){
        List<Comment> commentList = mock(ArrayList.class);
        when(commentRepository.findAllByProductId(1L)).thenReturn(commentList);
        when(commentList.isEmpty()).thenReturn(true);
        CommentException e = Assertions.assertThrows(CommentException.class,
                () -> commentService.getCommentsByProductId(1L));
        assertThat(e.getMessage(),is("Comment list not found"));
    }

    @Test
    void getCommentsByProductName_ShouldReturnCommentResponseDto_WhenProductNameIsCorrect(){
        List<Comment> commentList = mock(ArrayList.class);
        List<CommentResponseDto> commentResponseDtoList = mock(ArrayList.class);
        when(commentRepository.findAllByProductName("Product")).thenReturn(commentList);
        when(commentList.isEmpty()).thenReturn(false);
        Type listType = new TypeToken<List<CommentResponseDto>>() {
        }.getType();
        when(modelMapper.map(commentList,listType)).thenReturn(commentResponseDtoList);
        List<CommentResponseDto> result = commentService.getCommentsByProductName("Product");
        assertThat(result,is(commentResponseDtoList));
    }

    @Test
    void getCommentsByProductName_ShouldThrowsCommentException_WhenProductNameIsNotCorrect(){
        List<Comment> commentList = mock(ArrayList.class);
        when(commentRepository.findAllByProductName("Product")).thenReturn(commentList);
        when(commentList.isEmpty()).thenReturn(true);
        CommentException e = Assertions.assertThrows(CommentException.class,
                () -> commentService.getCommentsByProductName("Product"));
        assertThat(e.getMessage(),is("Comment list not found"));
    }

    @Test
    void createComment_ShouldReturnOK_WhenProductNameAndCustomerNameIsCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(customerRepository.findById(commentRequestDto.getCustomerId())).thenReturn(Optional.of(customer));

        commentService.createComment(commentRequestDto,1L);
        ArgumentCaptor<Comment> captor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepository).save(captor.capture());
    }

    @Test
    void createComment_ShouldThrowProductException_WhenProductIdIsNotCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        ProductException e = Assertions.assertThrows(ProductException.class,
                () -> commentService.createComment(commentRequestDto,1L));
        assertThat(e.getMessage(),is("Product id 1 not found"));
    }
    @Test
    void createComment_ShouldThrowCustomerException_WhenCustomerIdIsNotCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(customerRepository.findById(commentRequestDto.getCustomerId())).thenReturn(Optional.empty());
        CustomerException e = Assertions.assertThrows(CustomerException.class,
                () -> commentService.createComment(commentRequestDto,1L));
        assertThat(e.getMessage(),is("Customer id "+ commentRequestDto.getCustomerId()+" not found"));
    }

}
