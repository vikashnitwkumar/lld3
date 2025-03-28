package com.example.ecom.services;

import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Advertisement;
import com.example.ecom.repositories.UserRepository;

public interface AdsService {
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException;
}
