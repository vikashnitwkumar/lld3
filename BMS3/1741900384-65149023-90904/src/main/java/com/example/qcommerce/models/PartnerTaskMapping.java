package com.example.qcommerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Data
@Entity
public class PartnerTaskMapping extends BaseModel{
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Partner partner;
    @ManyToOne(cascade = CascadeType.REMOVE)  // Cascade delete when Task is deleted
    private Task task;
}
