package com.example.bmsbookticket.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity(name = "users")
public class User extends BaseModel{

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;
}
