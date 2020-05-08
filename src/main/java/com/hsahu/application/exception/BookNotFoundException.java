package com.hsahu.application.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
    public BookNotFoundException(String template, Object ...args) {
        this(String.format(template, args));
    }
}
