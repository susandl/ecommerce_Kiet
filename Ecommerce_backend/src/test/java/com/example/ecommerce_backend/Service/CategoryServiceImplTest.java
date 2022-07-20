package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Category;
import com.example.ecommerce_backend.Data.Repo.CategoryRepository;
import com.example.ecommerce_backend.Dto.Request.CategoryRequestDto;
import com.example.ecommerce_backend.Dto.Response.CategoryResponseDto;
import com.example.ecommerce_backend.Exception.CategoryException;
import com.example.ecommerce_backend.Service.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class CategoryServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    CategoryServiceImpl categoryService;
    Category category;
    CategoryResponseDto categoryResponseDto;
    CategoryRequestDto categoryRequestDto;

    @BeforeEach
    void before(){
        category = mock(Category.class);
        categoryResponseDto = mock(CategoryResponseDto.class);
        categoryRequestDto = mock(CategoryRequestDto.class);
    }

    @Test
    void getAllCategory_ShouldReturnCategoryList_WhenCategoryExist(){
        List<CategoryResponseDto> responseDtoList = mock(ArrayList.class);
        List<Category> categoryList = mock(ArrayList.class);
        Type listType = new TypeToken<List<CategoryResponseDto>>() {
        }.getType();
        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(modelMapper.map(categoryList,listType)).thenReturn(responseDtoList);
        List<CategoryResponseDto> result = categoryService.getAllCategories();
        assertThat(result,is(responseDtoList));
    }
    @Test
    void getAllCategory_ShouldReturnCategoryException_WhenCategoryDoesNotExist(){
        List<Category> categoryList = mock(ArrayList.class);
        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(categoryList.isEmpty()).thenReturn(true);
        CategoryException e = Assertions.assertThrows(CategoryException.class,
                () -> categoryService.getAllCategories());
        assertThat(e.getMessage(),is("Category list not found"));
    }
    @Test
    void getCategory_ShouldReturnCategory_WhenIdExist(){

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(modelMapper.map(category,CategoryResponseDto.class)).thenReturn(categoryResponseDto);
        CategoryResponseDto result = categoryService.getCategoryById(1L);
        assertThat(result,is(categoryResponseDto));
    }
    @Test
    void getCategory_ShouldReturnCategoryException_WhenIdIsNotExist(){
        CategoryException e = Assertions.assertThrows(CategoryException.class,
                () -> categoryService.getCategoryById(1L));
        assertThat(e.getMessage(),is("Category 1 not found"));
    }
    @Test
    void createCategory_ShouldReturnOK_WhenRequestIsValid(){
        when(categoryRepository.existsByName(categoryRequestDto.getName())).thenReturn(false);
        when(modelMapper.map(categoryRequestDto,Category.class)).thenReturn(category);
        categoryService.createCategory(categoryRequestDto);
        verify(categoryRepository).save(category);
    }
    @Test
    void createCategory_ShouldReturnCategoryException_WhenRequestIsInValid(){
        when(categoryRepository.existsByName(categoryRequestDto.getName())).thenReturn(true);
        CategoryException e = Assertions.assertThrows(CategoryException.class,
                () -> categoryService.createCategory(categoryRequestDto));
        assertThat(e.getMessage(),is("Category "+ categoryRequestDto.getName()+" exist"));
    }
    @Test
    void deleteCategory_ShouldReturnOK_WhenCategoryIdExist(){
        when(categoryRepository.existsById(1L)).thenReturn(true);
        categoryService.deleteCategoryById(1L);
        verify(categoryRepository).deleteById(1L);
    }
    @Test
    void deleteCategory_ShouldReturnCategoryException_WhenCategoryIdDoesNotExist(){
        when(categoryRepository.existsById(1L)).thenReturn(false);
        CategoryException e = Assertions.assertThrows(CategoryException.class,
                () -> categoryService.deleteCategoryById(1L));
        assertThat(e.getMessage(),is("category 1 does not exists"));
    }
    @Test
    void modifyCategory_ShouldReturnOK_WhenIdIsCorrect(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        categoryService.modifyCategory(1L,categoryRequestDto);
        verify(modelMapper).map(categoryRequestDto,category);
        verify(categoryRepository).save(category);
    }
    @Test
    void modifyCategory_ShouldReturnCategoryException_WhenIdIsNotCorrect(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        CategoryException e = Assertions.assertThrows(CategoryException.class,
                () -> categoryService.modifyCategory(1L,categoryRequestDto));
        assertThat(e.getMessage(),is("Category not found"));
    }

}
