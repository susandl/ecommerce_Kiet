package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
@Setter@Getter
@Embeddable
public class CommentId implements Serializable {
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "comment_date")
    private LocalDateTime timeStamp;
    public CommentId(Long customerId,Long productId){
        this.timeStamp = LocalDateTime.now();
        this.customerId=customerId;
        this.productId=productId;
    }

    public CommentId() {

    }


}
