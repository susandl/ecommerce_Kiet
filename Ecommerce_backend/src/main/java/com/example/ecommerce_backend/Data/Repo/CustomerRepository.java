package com.example.ecommerce_backend.Data.Repo;

import com.example.ecommerce_backend.Data.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    void deleteCustomerByName(String name);

    Customer findByName(String name);

    Boolean existsByName(String name);

    Optional<Customer> findById(Long id);

}
