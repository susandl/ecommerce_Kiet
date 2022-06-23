package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;
    @Column(name = "admin_name")
    private String name;
    @Column(name = "admin_pass")
    private String pass;
    public Admin(){

    }
    public Admin(String name,String pass){
        this.name=name;
        this.pass=pass;
    }
}
