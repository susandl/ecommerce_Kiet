package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name = "rating")
public class Rating {
    @EmbeddedId
    private RatingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "rating_point")
    private float point;

    public Rating(Customer customer,Product product,float point){
        this.id = new RatingId(customer.getId(),product.getId());
        this.customer = customer;
        this.product = product;
        this.point = point;
    }

    public Rating() {
    }
}
