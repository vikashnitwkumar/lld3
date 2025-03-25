package com.example.ecom.controllers;

import com.example.ecom.dtos.GetAdvertisementForUserRequestDto;
import com.example.ecom.dtos.GetAdvertisementForUserResponseDto;
import com.example.ecom.dtos.ResponseStatus;
import com.example.ecom.services.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdsController {
    AdsService adsService;

    @Autowired
    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    public GetAdvertisementForUserResponseDto getAdvertisementForUser(GetAdvertisementForUserRequestDto requestDto){
        GetAdvertisementForUserResponseDto responseDto = new GetAdvertisementForUserResponseDto();
        try {
            responseDto.setAdvertisement(adsService.getAdvertisementForUser(requestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
