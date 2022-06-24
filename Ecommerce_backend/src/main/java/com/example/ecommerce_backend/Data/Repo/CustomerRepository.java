package com.example.ecommerce_backend.Data.Repo;

import com.example.ecommerce_backend.Data.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}