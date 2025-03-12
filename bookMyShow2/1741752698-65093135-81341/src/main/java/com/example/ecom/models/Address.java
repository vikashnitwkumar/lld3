package com.example.ecom.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address extends BaseModel{
    @ManyToOne
    private User user;
    private String building;
    private int floor;
    private String roomNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
