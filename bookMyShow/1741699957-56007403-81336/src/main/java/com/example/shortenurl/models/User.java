package com.example.shortenurl.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity(name = "userTable")
public class User extends BaseModel{
    private String name;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private UserPlan userPlan;
}
