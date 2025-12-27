package com.diih.url.url.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class ExpiredUrl extends RuntimeException {


    public ExpiredUrl(String message) {
        super(message);
    }
}
