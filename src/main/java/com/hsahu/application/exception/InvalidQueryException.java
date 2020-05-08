package com.hsahu.application.exception;

public class InvalidQueryException extends Exception {
    public InvalidQueryException(String message) {
        super(message);
    }
    public InvalidQueryException(String template, Object ...args) {
        this(String.format(template, args));
    }
}
