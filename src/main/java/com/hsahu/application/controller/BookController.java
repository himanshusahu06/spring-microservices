package com.hsahu.application.controller;

import com.hsahu.application.dto.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class BookController {

    @GetMapping("/books")
    List<Book> getBooks() {
        final Book book = new Book(UUID.randomUUID(), "Astrophysics For People In Hurry", "Neil deGrasse Tyson");
        return Collections.singletonList(book);
    }
}
