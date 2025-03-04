package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.ParkingLot;
import com.scaler.parking_lot.models.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryTicketRepository implements TicketRepository{
    Map<Long, Ticket> map;
    public InMemoryTicketRepository(){
        map = new HashMap<>();
    }
    @Override
    public Ticket save(Ticket ticket) {
        if(ticket.getId() == 0){
            ticket.setId(map.size()+1);
        }
       map.put(ticket.getId(), ticket);
       return ticket;
    }

    @Override
    public Optional<Ticket> getTicketById(long ticketId) {
        return Optional.ofNullable(map.get(ticketId));
    }
}
