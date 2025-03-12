package com.example.bmsbookticket.services;

import com.example.bmsbookticket.models.*;
import com.example.bmsbookticket.repositories.ShowSeatRepository;
import com.example.bmsbookticket.repositories.TicketRepository;
import com.example.bmsbookticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    ShowSeatRepository showSeatRepository;
    UserRepository userRepository;
    TicketRepository ticketRepository;
    @Autowired
    public TicketServiceImpl(ShowSeatRepository showSeatRepository, UserRepository userRepository, TicketRepository ticketRepository) {
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        List<Integer> unAvailableSeatsIds = new ArrayList<>();
        List<ShowSeat> showSeatList = new ArrayList<>();
        List<Seat> seatList = new ArrayList<>();
        for (Integer showSeatId : showSeatIds) {
            ShowSeat showSeat = showSeatRepository.findById(showSeatId).orElseThrow(() -> new Exception("invalid Show seat"));
            if (showSeat.getStatus().equals( SeatStatus.AVAILABLE)) {
                showSeat.setStatus(SeatStatus.BLOCKED);
                showSeatList.add(showSeat);
                seatList.add(showSeat.getSeat());
            } else {
                unAvailableSeatsIds.add(showSeat.getSeat().getId());
            }
        }
        if (unAvailableSeatsIds.size() > 0) {
            throw new Exception("Seats are not available" + unAvailableSeatsIds);
        }
        showSeatRepository.saveAll(showSeatList);
        Ticket ticket = new Ticket();
        ticket.setShow(showSeatList.get(0).getShow());
        ticket.setTimeOfBooking(new Date());
        ticket.setStatus(TicketStatus.UNPAID);
        ticket.setUser(user);
        ticket.setSeats(seatList);
        return ticketRepository.save(ticket);
    }
}
