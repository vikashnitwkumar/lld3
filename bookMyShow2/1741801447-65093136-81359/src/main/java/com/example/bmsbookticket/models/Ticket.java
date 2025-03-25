package com.example.bmsbookticket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Ticket extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToMany
    @JoinTable(name = "ticket_seat", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
    List<Seat> seats;
    private Date timeOfBooking;
    @ManyToOne
    private User user;
    @Enumerated
    private TicketStatus status;
}
