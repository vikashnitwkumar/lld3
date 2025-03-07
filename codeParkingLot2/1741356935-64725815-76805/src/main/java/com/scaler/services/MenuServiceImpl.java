package com.scaler.services;

import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.MenuRepository;
import com.scaler.repositories.UserRepository;

import java.util.Optional;

public class MenuServiceImpl implements MenuService {
    UserRepository userRepository;
    MenuRepository menuRepository;

    public MenuServiceImpl(UserRepository userRepository, MenuRepository menuRepository) {
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuItem addMenuItem(long userId, String name, double price, String dietaryRequirement, String itemType, String description) throws UserNotFoundException, UnAuthorizedAccess {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("user not found");
        if (userOptional.get().getUserType() != UserType.ADMIN) throw new UnAuthorizedAccess("user is not an admin");
        MenuItem menuItem = new MenuItem();
        menuItem.setItemType(ItemType.valueOf(itemType));
        menuItem.setDescription(description);
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setDietaryRequirement(DietaryRequirement.valueOf(dietaryRequirement));
        return menuRepository.add(menuItem);
    }
}