package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
}
