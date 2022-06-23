package com.example.ecommerce_backend.Data.Repo;

import com.example.ecommerce_backend.Data.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
