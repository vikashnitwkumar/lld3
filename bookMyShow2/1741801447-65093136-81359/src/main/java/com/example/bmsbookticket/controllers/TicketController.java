package com.example.bmsbookticket.controllers;

import com.example.bmsbookticket.dtos.BookTicketRequestDTO;
import com.example.bmsbookticket.dtos.BookTicketResponseDTO;
import com.example.bmsbookticket.dtos.ResponseStatus;
import com.example.bmsbookticket.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO requestDTO){
        BookTicketResponseDTO responseDto = new BookTicketResponseDTO();
        try{
            responseDto.setTicket(
                    ticketService.bookTicket(requestDTO.getShowSeatIds(), requestDTO.getUserId())
            );
            responseDto.setStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
