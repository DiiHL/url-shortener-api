package com.diih.url.url.repository;

import com.diih.url.url.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;


public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);
    void deleteByShortUrl(String shortUrl);

    void deleteAllByTimeValidBefore(LocalDateTime time);
}
