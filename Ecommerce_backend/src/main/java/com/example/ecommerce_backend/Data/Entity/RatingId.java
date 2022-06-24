package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class RatingId implements Serializable {
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "rating_date")
    private LocalDateTime timeStamp;
    public RatingId(Long customerId,Long productId){
        this.timeStamp = LocalDateTime.now();
        this.customerId=customerId;
        this.productId=productId;
    }

    public RatingId() {

    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof RatingId ratingId)) return false;
        return Objects.equals(getCustomerId(),ratingId.getCustomerId()) &&
                Objects.equals(getProductId(),ratingId.getProductId()) &&
                Objects.equals(getTimeStamp(),ratingId.getTimeStamp());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getCustomerId(),getProductId(),getTimeStamp());
    }
}
