package com.scaler.controllers;

import com.scaler.dtos.CalculateRevenueRequestDto;
import com.scaler.dtos.CalculateRevenueResponseDto;
import com.scaler.dtos.ResponseStatus;
import com.scaler.models.AggregatedRevenue;
import com.scaler.services.RevenueService;

public class RevenueController {

    private RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    public CalculateRevenueResponseDto calculateRevenue(CalculateRevenueRequestDto requestDto){
       CalculateRevenueResponseDto responseDto = new CalculateRevenueResponseDto();
        try {
           responseDto.setAggregatedRevenue(
                   revenueService.calculateRevenue(requestDto.getUserId(),
                           requestDto.getRevenueQueryType()
                   )
           );
           responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
