package com.example.ecom.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "OrderTable")
public class Order extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Address deliveryAddress;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    @Enumerated(value = EnumType.ORDINAL)
    private OrderStatus orderStatus;
}
