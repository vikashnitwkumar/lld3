package com.example.bmsbookticket.repositories;

import com.example.bmsbookticket.models.Movie;
import com.example.bmsbookticket.models.Rating;
import com.example.bmsbookticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    List<Rating> findByMovie(Movie movie);
    Optional<Rating> findByMovieAndUser(Movie movie, User user);
}
