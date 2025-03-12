package com.example.bmsbookticket.services;

import com.example.bmsbookticket.exceptions.MovieNotFoundException;
import com.example.bmsbookticket.exceptions.UserNotFoundException;
import com.example.bmsbookticket.models.Movie;
import com.example.bmsbookticket.models.Rating;
import com.example.bmsbookticket.models.User;
import com.example.bmsbookticket.repositories.MovieRepository;
import com.example.bmsbookticket.repositories.RatingRepository;
import com.example.bmsbookticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingsService{
    UserRepository userRepository;
    RatingRepository ratingRepository;
    @Autowired
    public RatingServiceImpl(UserRepository userRepository, RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    MovieRepository movieRepository;
    @Override
    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException {
       Optional<User> userOptional = userRepository.findById(userId);
       if(userOptional.isEmpty()) throw new UserNotFoundException("no user");
       Optional<Movie> movieOptional = movieRepository.findById(movieId);
       if(movieOptional.isEmpty()) throw new MovieNotFoundException("no movie");
       Optional<Rating> ratingOptional = ratingRepository.findByMovieAndUser(movieOptional.get(), userOptional.get());

       Rating rating1;
       if(ratingOptional.isEmpty()) {
           rating1 = new Rating();
           rating1.setMovie(movieOptional.get());
           rating1.setUser(userOptional.get());
       }
       else {
           rating1 = ratingOptional.get();
       }
       rating1.setRating(rating);
       return ratingRepository.save(rating1);
    }

    @Override
    public double getAverageRating(int movieId) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()) throw new MovieNotFoundException("no movie");
        List<Rating> ratingList = ratingRepository.findByMovie(movieOptional.get());
        long totalRating = 0;
        double result = 0.0;
        if(ratingList.size()!=0){
            for (Rating itr : ratingList){
                totalRating += itr.getRating();
            }
            result = (double)totalRating/(ratingList.size());
            return  result;
        }
        return result;
    }
}
