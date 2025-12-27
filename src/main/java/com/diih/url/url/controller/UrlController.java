package com.diih.url.url.controller;

import com.diih.url.url.DTO.UrlRequest;
import com.diih.url.url.DTO.shortUrlResponse;
import com.diih.url.url.entity.Url;
import com.diih.url.url.exception.ExpiredUrl;
import com.diih.url.url.services.UrlService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/short")
    public ResponseEntity<Object> createShortUrl(@RequestBody UrlRequest originalUrl, HttpServletRequest request) {
        String url = originalUrl.url();
        String urlResponse = urlService.shortUrl(url);

        String aShort = request.getRequestURL().toString().replace("short", urlResponse);
        return ResponseEntity.ok(new shortUrlResponse(aShort, LocalDateTime.now().plusMinutes(10)));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirectToOriginalUrl(@PathVariable String shortUrl) {
        return urlService.redirectUrl(shortUrl)
                    .map(url -> ResponseEntity
                            .status(HttpStatus.FOUND)
                            .header(HttpHeaders.LOCATION, url.getOriginalUrl())
                            .build())
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/links")
    public ResponseEntity<Object> getLinks() {
        List<Url> links = urlService.links();
        return ResponseEntity.ok(links);
    }

    @DeleteMapping("/delete/{shortUrl}")
    public ResponseEntity<?> deleteShortUrl(@PathVariable String shortUrl) {
        try {
            urlService.deleteShortUrl(shortUrl);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Short URL not found");
        }
    }
}
