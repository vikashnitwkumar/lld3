package com.example.shortenurl.repositories;

import com.example.shortenurl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
