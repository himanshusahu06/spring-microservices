package org.hsahu.springboot.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.hsahu.springboot.dto.ApiErrorResponse;
import org.hsahu.springboot.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Centralise exception handling across all controllers
 */
@Slf4j
@RestController
@ControllerAdvice
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * this class handles exceptions defined inside @ExceptionHandler annotation
     *
     * @param ex         exception caught
     * @param webRequest request context
     * @return error response object
     */
    @ExceptionHandler({
            UserNotFoundException.class
    })
    public ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiErrorResponse errorResponse = new ApiErrorResponse(new Date(), ex.getMessage(),
                webRequest.getDescription(false), httpStatus.value(), httpStatus.getReasonPhrase());
        log.error("Request failed with following error: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    /**
     * override existing handleMethodArgumentNotValid method to return error response
     * when bad request is made by user
     *
     * @param ex      method argument not valid exception thrown by upstream method
     * @param headers request headers
     * @param status  response http status
     * @param request request context
     * @return error response
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(new Date(), "Request validation failed.",
                ex.getBindingResult().toString(), status.value(), status.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, status);
    }
}
