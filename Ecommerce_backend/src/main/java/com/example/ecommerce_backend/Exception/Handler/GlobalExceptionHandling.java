package com.example.ecommerce_backend.Exception.Handler;

import com.example.ecommerce_backend.Exception.CategoryNotFound;
import com.example.ecommerce_backend.Exception.CustomerNotFound;
import com.example.ecommerce_backend.Exception.ProductNotFound;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomerNotFound.class})
    protected ResponseEntity<Object> handlerCustomerNotFound(CustomerNotFound e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProductNotFound.class})
    protected ResponseEntity<Object> handlerProductNotFound(ProductNotFound e) {
        return new ResponseEntity<>(new ApiError(e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CategoryNotFound.class})
    protected ResponseEntity<Object> handlerCategoryNotFound(CategoryNotFound e) {
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
