package com.hsahu.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Book {
    private final UUID id;
    private final String name;
    private final String author;
}