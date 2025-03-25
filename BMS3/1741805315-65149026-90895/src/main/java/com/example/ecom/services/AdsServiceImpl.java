package com.example.ecom.services;

import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Advertisement;
import com.example.ecom.models.Preference;
import com.example.ecom.models.User;
import com.example.ecom.repositories.AdvertisementRepository;
import com.example.ecom.repositories.PreferencesRepository;
import com.example.ecom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService{
    UserRepository userRepository;
    AdvertisementRepository advertisementRepository;
    PreferencesRepository preferencesRepository;
    @Autowired
    public AdsServiceImpl(UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Advertisement> advertisements = new ArrayList<>();
        for (Preference preference : user.getPreferences()) {
            advertisementRepository.findByPreference(preference).forEach(advertisements::add);
        }
        if (advertisements.isEmpty()) {
            return null;
        }
        return advertisements.get(0);
    }
}
