package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(name = "category_name")
    private String name;
    @Column(name = "category_details")
    private String details;
    @OneToMany
    @JoinColumn(name = "product_id")
    List<Product> productList;
    public Category(){
    }
    public Category(String name,String details){
        this.name=name;
        this.details=details;
    }
}