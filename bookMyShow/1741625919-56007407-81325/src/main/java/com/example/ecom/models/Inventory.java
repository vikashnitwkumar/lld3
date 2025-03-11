package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = false)
public class Inventory extends BaseModel{
    @OneToOne
    private Product product;
    private int quantity;
}
