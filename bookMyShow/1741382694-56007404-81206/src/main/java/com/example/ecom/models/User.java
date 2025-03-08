package com.example.ecom.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "buyer_user")
public class User extends BaseModel {
    private String name;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "UserAddress",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;
}
