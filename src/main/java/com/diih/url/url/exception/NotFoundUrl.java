package com.diih.url.url.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundUrl extends RuntimeException {


    public NotFoundUrl(String message) {
        super(message);
    }
}
