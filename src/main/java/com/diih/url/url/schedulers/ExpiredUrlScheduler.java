package com.diih.url.url.schedulers;

import com.diih.url.url.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExpiredUrlScheduler {

    @Autowired
    private UrlRepository urlRepository;

    @Transactional
    @Scheduled(cron = "0 */10 * * * *")
    public void cleanExpiredUrls(){
        urlRepository.deleteAllByTimeValidBefore(LocalDateTime.now());
    }

}
