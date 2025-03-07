package com.scaler.controllers;

import com.scaler.dtos.*;
import com.scaler.models.DietaryRequirement;
import com.scaler.models.ItemType;
import com.scaler.models.MenuItem;
import com.scaler.services.MenuService;

import java.util.List;

public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    public AddMenuItemResponseDto addMenuItem(AddMenuItemRequestDto requestDto){
        AddMenuItemResponseDto responseDto = new AddMenuItemResponseDto();
        try{
            MenuItem menuItem = menuService.addMenuItem(requestDto.getUserId(),
                    requestDto.getName(),
                    requestDto.getPrice(),
//                    DietaryRequirement.valueOf(requestDto.getDietaryRequirement()),
                    requestDto.getDietaryRequirement(),
//                    ItemType.valueOf(requestDto.getItemType()),
                    requestDto.getItemType(),
                    requestDto.getDescription());
            responseDto.setMenuItem(menuItem);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return  responseDto;
    }
}
