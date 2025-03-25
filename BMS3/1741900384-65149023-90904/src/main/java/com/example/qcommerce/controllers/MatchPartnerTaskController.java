package com.example.qcommerce.controllers;

import com.example.qcommerce.dtos.MatchPartnerTaskRequestDto;
import com.example.qcommerce.dtos.MatchPartnerTaskResponseDto;
import com.example.qcommerce.dtos.ResponseStatus;
import com.example.qcommerce.services.MatchPartnerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MatchPartnerTaskController {
    MatchPartnerTaskService matchPartnerTaskService;
    @Autowired
    public MatchPartnerTaskController(MatchPartnerTaskService matchPartnerTaskService) {
        this.matchPartnerTaskService = matchPartnerTaskService;
    }

    public MatchPartnerTaskResponseDto matchPartnersAndTasks(MatchPartnerTaskRequestDto requestDto){
        MatchPartnerTaskResponseDto responseDto = new MatchPartnerTaskResponseDto();
        try {
            responseDto.setPartnerTaskMappings(
                    matchPartnerTaskService.matchPartnersAndTasks(
                            requestDto.getPartnerIds(),
                            requestDto.getTaskIds()
                    )
            );
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            // Handle exceptions here
            responseDto.setResponseStatus(ResponseStatus.FAILURE);

        }
        return responseDto;
    }
}
