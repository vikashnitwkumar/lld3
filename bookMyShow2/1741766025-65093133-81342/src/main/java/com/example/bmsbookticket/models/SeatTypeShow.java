package com.example.bmsbookticket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "seat_type_shows")
public class SeatTypeShow extends BaseModel{
    @ManyToOne
    private Show show;

    @Enumerated(value = EnumType.ORDINAL)
    private SeatType seatType;
    private double price;
}

