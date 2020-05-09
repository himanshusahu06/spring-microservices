package com.hsahu.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
public class Book {
    private final UUID isbn;
    private final String title;
    private final String author;
}