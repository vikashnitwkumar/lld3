package com.example.shortenurl.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ShortenedUrl extends BaseModel{
    private String originalUrl;
    @Column(unique = true)
    private String shortUrl;
    private long expiresAt;
    @ManyToOne(optional = false)
    private User user;
}
