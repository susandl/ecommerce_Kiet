package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Rating;
import com.example.ecommerce_backend.Dto.Request.RatingRequestDto;
import com.example.ecommerce_backend.Dto.Response.RatingResponseDto;

import java.util.List;

public interface RatingService {
    List<RatingResponseDto> getRatingsByProductId(Long id);

    List<RatingResponseDto> getRatingsByProductName(String name);

    void createRating(RatingRequestDto ratingRequestDto);
}
