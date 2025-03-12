package com.example.bmsbookticket.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "userRating")
public class User extends BaseModel{
    private String name;
    private String email;
}
