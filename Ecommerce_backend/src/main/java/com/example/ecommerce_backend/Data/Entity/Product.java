package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_details")
    private String details;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Comment> commentList;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Rating> ratingList;
    public Product(){

    }

}
