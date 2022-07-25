package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Entity.Product;
import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Data.Repo.ProductRepository;
import com.example.ecommerce_backend.Dto.Request.ProductRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Dto.Response.ProductResponseDto;
import com.example.ecommerce_backend.Exception.CategoryException;
import com.example.ecommerce_backend.Exception.ProductException;
import com.example.ecommerce_backend.Service.Impl.ProductServiceImpl;
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
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    ProductServiceImpl productService;

    Product product;
    ProductRequestDto productRequestDto;
    ProductResponseDto productResponseDto;

    @BeforeEach
    void before(){
        product = mock(Product.class);
        productRequestDto = mock(ProductRequestDto.class);
        productResponseDto = mock(ProductResponseDto.class);
    }

    @Test
    void getProductListByCategoryId_ShouldReturnProductList_WhenCategoryNameIsCorrect(){
        List<Product> productList = mock(ArrayList.class);
        List<ProductResponseDto> productResponseDtoList = mock(ArrayList.class);
        when(productRepository.findAllProductByCategoryId(1L)).thenReturn(productList);
        Type listType = new TypeToken<List<ProductResponseDto>>() {
        }.getType();
        when(modelMapper.map(productList,listType)).thenReturn(productResponseDtoList);
        List<ProductResponseDto> result = productService.getProductsByCategoryId(1L);
        assertThat(result,is(productResponseDtoList));
    }

    @Test
    void getProductListByCategoryId_ShouldReturnProductException_WhenCategoryNameIsNotCorrect(){
        List<Product> productList = mock(ArrayList.class);
        when(productRepository.findAllProductByCategoryId(1L)).thenReturn(productList);
        when(productList.isEmpty()).thenReturn(true);
        ProductException e = Assertions.assertThrows(ProductException.class,
                () -> productService.getProductsByCategoryId(1L));
        assertThat(e.getMessage(),is("Product List not found"));
    }

    @Test
    void getProductById_ShouldReturnProductResponseDto_WhenIdIsCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(modelMapper.map(product,ProductResponseDto.class)).thenReturn(productResponseDto);
        ProductResponseDto result = productService.getProductById(1L);
        assertThat(result,is(productResponseDto));
    }

    @Test
    void getProductById_ShouldReturnProductException_WhenIdIsNotCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        ProductException e = Assertions.assertThrows(ProductException.class,
                () -> productService.getProductById(1L));
        assertThat(e.getMessage(),is("Product id 1 not found"));
    }

    @Test
    void getAllProduct_ShouldReturnProductListDto_WhenProductExist(){
        List<ProductResponseDto> responseDtoList = mock(ArrayList.class);
        List<Product> productList = mock(ArrayList.class);
        Type listType = new TypeToken<List<ProductResponseDto>>() {
        }.getType();
        when(productRepository.findAll()).thenReturn(productList);
        when(modelMapper.map(productList,listType)).thenReturn(responseDtoList);
        List<ProductResponseDto> result = productService.getAllProducts();
        assertThat(result,is(responseDtoList));
    }

    @Test
    void getAllProduct_ShouldReturnProductException_WhenProductNotExist(){
        List<Product> productList = mock(ArrayList.class);
        when(productRepository.findAll()).thenReturn(productList);
        when(productList.isEmpty()).thenReturn(true);
        ProductException e = Assertions.assertThrows(ProductException.class,
                () -> productService.getAllProducts());
        assertThat(e.getMessage(),is("Product List not found"));
    }

    @Test
    void createProduct_ShouldReturnOK_WhenProductRequestDtoIsCorrect(){
        when(productRepository.existsByName(productRequestDto.getName())).thenReturn(false);
        when(categoryRepository.existsByName(productRequestDto.getCategoryName())).thenReturn(true);
        productService.createProduct(productRequestDto);
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());
    }

    @Test
    void createProduct_ShouldReturnProductException_WhenProductNameExist(){
        when(productRepository.existsByName(productRequestDto.getName())).thenReturn(true);
        ProductException e = Assertions.assertThrows(ProductException.class,
                () -> productService.createProduct(productRequestDto));
        assertThat(e.getMessage(),is("Product " + productRequestDto.getName() + " exists" ));
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository,never()).save(captor.capture());
    }

    @Test
    void createProduct_ShouldReturnCategoryException_WhenCategoryNameNotExist(){
        when(categoryRepository.existsByName(productRequestDto.getCategoryName())).thenReturn(false);
        CategoryException e = Assertions.assertThrows(CategoryException.class,
                () -> productService.createProduct(productRequestDto));
        assertThat(e.getMessage(),is("Category " + productRequestDto.getCategoryName() + " not found" ));
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository,never()).save(captor.capture());
    }

    @Test
    void deleteProduct_ShouldReturnOK_WhenProductIdCorrect(){
        when(productRepository.existsById(1L)).thenReturn(true);
        productService.deleteProductById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void deleteProduct_ShouldThrowsProductException_WhenProductIdNotCorrect(){
        when(productRepository.existsById(1L)).thenReturn(false);
        ProductException e = Assertions.assertThrows(ProductException.class,
                () -> productService.deleteProductById(1L));
        assertThat(e.getMessage(),is("product id 1 does not exists"));
    }

    @Test
    void modifyProduct_ShouldReturnOK_WhenIdANDProductRequestIsCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.existsByName(productRequestDto.getCategoryName())).thenReturn(true);
        productService.modifyProduct(1L,productRequestDto);
        verify(productRepository).save(product);
    }

    @Test
    void modifyProduct_ShouldThrowsProductException_WhenProductIdIsNotCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        ProductException e = Assertions.assertThrows(ProductException.class,
                () -> productService.modifyProduct(1L,productRequestDto));
        assertThat(e.getMessage(),is("Product not found"));
    }

    @Test
    void modifyProduct_ShouldThrowsCategoryException_WhenCategoryNameIsNotCorrect(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.existsByName(productRequestDto.getCategoryName())).thenReturn(false);
        CategoryException e = Assertions.assertThrows(CategoryException.class,
                () -> productService.modifyProduct(1L,productRequestDto));
        assertThat(e.getMessage(),is("Category " + productRequestDto.getCategoryName() + " not found"));
    }


}
