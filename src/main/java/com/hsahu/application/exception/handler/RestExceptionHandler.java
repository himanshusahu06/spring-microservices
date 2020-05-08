package com.hsahu.application.exception.handler;

import com.hsahu.application.dto.error.ApiError;
import com.hsahu.application.exception.BookNotFoundException;
import com.hsahu.application.exception.InvalidQueryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Rest Exception Mapper for Rest APIs
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            BookNotFoundException.class,
            InvalidQueryException.class
    })
    ResponseEntity<Object> handleCustomException(Exception ex, WebRequest webRequest) throws Exception {
        String url = ((ServletWebRequest)webRequest).getRequest().getServletPath();
        if (ex instanceof BookNotFoundException) {
            return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, url, ex));
        }
        if (ex instanceof InvalidQueryException) {
            return  buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, url, ex));
        }
        throw ex;
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        log.error(apiError.toString());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
