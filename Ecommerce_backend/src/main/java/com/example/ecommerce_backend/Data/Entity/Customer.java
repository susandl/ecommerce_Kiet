package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_pass")
    private String pass;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Comment> commentList;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Rating> ratingList;


    public Customer(){}
    public Customer(String name,String pass){
        this.name=name;
        this.pass=pass;
    }

}
