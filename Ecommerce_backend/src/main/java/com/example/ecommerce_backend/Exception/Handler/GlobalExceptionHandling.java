package com.example.ecommerce_backend.Exception.Handler;

import com.example.ecommerce_backend.Exception.CategoryException;
import com.example.ecommerce_backend.Exception.CustomerException;
import com.example.ecommerce_backend.Exception.ProductException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomerException.class})
    protected ResponseEntity<Object> handlerCustomerNotFound(CustomerException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProductException.class})
    protected ResponseEntity<Object> handlerProductNotFound(ProductException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CategoryException.class})
    protected ResponseEntity<Object> handlerCategoryNotFound(CategoryException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nullable HttpHeaders headers, @Nullable HttpStatus status, @Nullable WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }


}
