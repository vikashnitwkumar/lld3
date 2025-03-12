package com.example.ecom.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderDetail extends BaseModel{
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private int quantity;
}
