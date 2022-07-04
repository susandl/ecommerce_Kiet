package com.example.ecommerce_backend.Data.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class CommentId implements Serializable {
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "comment_date")
    private LocalDateTime timeStamp;

    public CommentId(Long customerId, Long productId) {
        this.timeStamp = LocalDateTime.now();
        this.customerId = customerId;
        this.productId = productId;
    }

    public CommentId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(this.customerId, commentId.customerId) &&
                Objects.equals(this.productId, commentId.productId) &&
                Objects.equals(this.timeStamp, commentId.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId, timeStamp);
    }
}
