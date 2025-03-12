package com.example.ecom.controllers;

import com.example.ecom.dtos.PlaceOrderRequestDto;
import com.example.ecom.dtos.PlaceOrderResponseDto;
import com.example.ecom.dtos.ResponseStatus;
import com.example.ecom.models.Order;
import com.example.ecom.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto requestDto) {
        PlaceOrderResponseDto responseDto = new PlaceOrderResponseDto();
        try {
            responseDto.setOrder(
                    orderService.placeOrder(
                            requestDto.getUserId(),
                            requestDto.getAddressId(),
                            requestDto.getOrderDetails()
                    )
            );
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

}
