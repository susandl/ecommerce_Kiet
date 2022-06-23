package com.example.ecommerce_backend.Data.Repo;

import com.example.ecommerce_backend.Data.Entity.Rating;
import com.example.ecommerce_backend.Data.Entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
}
