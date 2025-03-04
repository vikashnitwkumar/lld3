package com.scaler.parking_lot.controllers;

import com.scaler.parking_lot.dtos.GenerateTicketRequestDto;
import com.scaler.parking_lot.dtos.GenerateTicketResponseDto;
import com.scaler.parking_lot.dtos.ResponseStatus;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        try{
           Ticket ticket = ticketService.generateTicket(requestDto.getGateId(), requestDto.getRegistrationNumber(), requestDto.getVehicleType());
           GenerateTicketResponseDto ticketResponseDto = new GenerateTicketResponseDto();
           ticketResponseDto.setTicket(ticket);
           ticketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
           return ticketResponseDto;
        } catch (Exception e) {
            GenerateTicketResponseDto ticketResponseDto = new GenerateTicketResponseDto();
            ticketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            return ticketResponseDto;
        }
    }
}
