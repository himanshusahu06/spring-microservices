package com.hsahu.application.dto.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * API  error response entity
 */
@ToString
@Getter
public class ApiError {
    private final String timestamp;
    private String message;
    private HttpStatus status;
    private String reason;
    private String url;

    private  ApiError() {
        super();
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));
    }

    public ApiError(HttpStatus httpStatus, String url) {
        this();
        this.url = url;
        this.status = httpStatus;
        this.reason = httpStatus.getReasonPhrase();
    }

    public ApiError(HttpStatus httpStatus, String url, String message) {
        this(httpStatus, url);
        this.message = message;
    }

    public  ApiError(HttpStatus httpStatus, String url, Throwable ex) {
        this(httpStatus, url, ex.getMessage());
    }
}