package com.example.shortenurl.services;

import com.example.shortenurl.exceptions.UrlNotFoundException;
import com.example.shortenurl.exceptions.UserNotFoundException;
import com.example.shortenurl.models.ShortenedUrl;
import com.example.shortenurl.models.UrlAccessLog;
import com.example.shortenurl.models.User;
import com.example.shortenurl.models.UserPlan;
import com.example.shortenurl.repositories.ShortenedUrlRepository;
import com.example.shortenurl.repositories.UrlAccessLogRepository;
import com.example.shortenurl.repositories.UserRepository;
import com.example.shortenurl.utils.ShortUrlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    UserRepository userRepository;
    ShortenedUrlRepository shortenedUrlRepository;
    UrlAccessLogRepository urlAccessLogRepository;
    @Autowired
    public UrlServiceImpl(UserRepository userRepository, ShortenedUrlRepository shortenedUrlRepository, UrlAccessLogRepository urlAccessLogRepository) {
        this.userRepository = userRepository;
        this.shortenedUrlRepository = shortenedUrlRepository;
        this.urlAccessLogRepository = urlAccessLogRepository;
    }

    @Override
    public ShortenedUrl shortenUrl(String url, int userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("user not found");
        String shortUrl = ShortUrlGenerator.generateShortUrl();
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setShortUrl(shortUrl);
        shortenedUrl.setOriginalUrl(url);
        shortenedUrl.setUser(userOptional.get());
        shortenedUrl.setExpiresAt(
               getExpiry( userOptional.get().getUserPlan())
        );
        return shortenedUrlRepository.save(shortenedUrl);
    }

    @Override
    public String resolveShortenedUrl(String shortUrl) throws UrlNotFoundException {
        Optional<ShortenedUrl> shortenedUrlOptional = shortenedUrlRepository.findByShortUrl(shortUrl);
        if (shortenedUrlOptional.isEmpty()) throw new UrlNotFoundException("No shortened url");
        ShortenedUrl shortenedUrl = shortenedUrlOptional.get();
        if(shortenedUrl.getExpiresAt() < System.currentTimeMillis()) {
            shortenedUrlRepository.delete(shortenedUrl);
            throw new UrlNotFoundException("No shortened url");
        }
        UrlAccessLog urlAccessLog = new UrlAccessLog();
        urlAccessLog.setShortenedUrl(shortenedUrl);
        urlAccessLog.setAccessedAt(System.currentTimeMillis());
        urlAccessLogRepository.save(urlAccessLog);
        return shortenedUrl.getOriginalUrl();
    }

    private long getExpiry(UserPlan userPlan){
        long currSec = System.currentTimeMillis();
        long secInAday = 24*3600;
        switch (userPlan){
            case FREE -> {
              return   currSec + secInAday;
            }
            case TEAM -> {
                return currSec + secInAday*7;
            }
            case BUSINESS -> {
                return currSec + secInAday*30;
            }
            case ENTERPRISE -> {
                return currSec + secInAday*365;
            }
        }
        return currSec;
    }
}
