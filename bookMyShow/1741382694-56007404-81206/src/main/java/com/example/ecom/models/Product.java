package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "Product")
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    @ManyToOne()
    @JoinColumn(name = "seller_id",  nullable = false)
    private Seller seller;
}
