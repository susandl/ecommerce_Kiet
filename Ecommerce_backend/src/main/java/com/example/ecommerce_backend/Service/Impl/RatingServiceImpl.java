package com.example.ecommerce_backend.Service.Impl;

import com.example.ecommerce_backend.Data.Entity.Rating;
import com.example.ecommerce_backend.Data.Repo.RatingRepository;
import com.example.ecommerce_backend.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    @Override
    public List<Rating> getRatings() {
        return this.ratingRepository.findAll();
    }

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository=ratingRepository;
    }
}
