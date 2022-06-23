package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @EmbeddedId
    private CommentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "comment_details")
    private String details;

    public Comment(Customer customer,Product product,String content){
        this.id = new CommentId(customer.getId(),product.getId());
        this.customer = customer;
        this.product = product;
        this.details = content;
    }

    public Comment() {
    }
}