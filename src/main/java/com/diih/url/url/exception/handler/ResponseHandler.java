package com.diih.url.url.exception.handler;

import com.diih.url.url.exception.ExceptionResponse;
import com.diih.url.url.exception.ExpiredUrl;
import com.diih.url.url.exception.NotFoundUrl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class ResponseHandler {

    @ExceptionHandler(ExpiredUrl.class)
    public final ResponseEntity<ExceptionResponse> handleExpiredUrl(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    @ExceptionHandler(NotFoundUrl.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundUrl(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
