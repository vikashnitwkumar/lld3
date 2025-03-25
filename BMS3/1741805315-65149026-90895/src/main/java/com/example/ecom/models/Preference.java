package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "preferences")
public class Preference extends BaseModel{
    private String category;
    private String description;
    private Date createdAt;
}
