package com.example.bmsbookticket.services;

import com.example.bmsbookticket.models.User;
import com.example.bmsbookticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();  // Dependency Injection for better efficiency
    }

    @Override
    public User signupUser(String name, String email, String password) throws Exception {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("User already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public boolean login(String email, String password) throws Exception {
        // Find user by email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User not found"));

        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}
