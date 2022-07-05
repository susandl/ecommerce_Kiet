package com.example.ecommerce_backend.Data.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    private Long id;
    private String name;
    private String pass;
    private List<Comment> commentList;
    private List<Rating> ratingList;
    private Set<Role> role = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "customer_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "customer_pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_role",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Customer() {
    }

    public Customer(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }


}
