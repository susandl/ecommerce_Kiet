package com.example.ecommerce_backend.Data.Repo;

import com.example.ecommerce_backend.Data.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    boolean existsByName(String role);
}
