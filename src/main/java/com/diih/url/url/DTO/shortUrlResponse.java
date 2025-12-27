package com.diih.url.url.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record shortUrlResponse(String url, @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime expireAt) {
}
