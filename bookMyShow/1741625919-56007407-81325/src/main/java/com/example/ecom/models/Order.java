package com.example.ecom.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "OrderTable")
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
}
