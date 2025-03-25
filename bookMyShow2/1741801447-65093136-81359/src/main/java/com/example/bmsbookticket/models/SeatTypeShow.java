package com.example.bmsbookticket.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class SeatTypeShow extends BaseModel{
   @ManyToOne
    private Show show;
    @Enumerated
    private SeatType seatType;
    private double price;
}
