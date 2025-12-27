package com.diih.url.url.services;

import com.diih.url.url.entity.Url;
import com.diih.url.url.exception.ExpiredUrl;
import com.diih.url.url.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortUrl(String responseUrl) {
        String http = "https://";
        String httpAlt = "http://";

        if (!responseUrl.startsWith(http) && !responseUrl.startsWith(httpAlt)) {
            responseUrl = http + responseUrl;
        }

        String shortUrl = generateShortUrl(responseUrl);

        Url url = new Url();
        url.setOriginalUrl(responseUrl);
        url.setShortUrl(shortUrl);
        url.setTimeValid(LocalDateTime.now().plusMinutes(10));
        urlRepository.save(url);


        return shortUrl;
    }

    public Optional<Url> redirectUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl).orElseThrow(() -> new EntityNotFoundException("URL not found"));

        if (url.getTimeValid().isBefore(LocalDateTime.now())) {
            throw new ExpiredUrl("URL is expired");
        }
        return Optional.of(url);
    }

    public List<Url> links() {
        return urlRepository.findAll();
    }

    @Transactional
    public void deleteShortUrl(String shortUrl) {
        urlRepository.deleteByShortUrl(shortUrl);
    }

    private String generateShortUrl(String Url) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
        int length = 5 + random.nextInt(6);
        for (int i = 0; i < length; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }

        return shortUrl.toString();
    }
}
