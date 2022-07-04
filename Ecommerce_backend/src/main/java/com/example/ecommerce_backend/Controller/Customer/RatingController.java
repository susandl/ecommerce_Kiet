package com.example.ecommerce_backend.Controller.Customer;

import com.example.ecommerce_backend.Dto.Request.CommentRequestDto;
import com.example.ecommerce_backend.Dto.Request.RatingRequestDto;
import com.example.ecommerce_backend.Dto.Response.CommentResponseDto;
import com.example.ecommerce_backend.Dto.Response.RatingResponseDto;
import com.example.ecommerce_backend.Service.CommentService;
import com.example.ecommerce_backend.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer/rating")
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/{id}")
    public List<RatingResponseDto> getRatingsByProductId(@PathVariable Long id) {
        return this.ratingService.getRatingsByProductId(id);
    }

    @RequestMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRating(@Valid @RequestBody RatingRequestDto ratingRequestDto) {
        this.ratingService.createRating(ratingRequestDto);
    }
}