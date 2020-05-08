package com.hsahu.application.biz;

import com.hsahu.application.dto.Book;
import com.hsahu.application.exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookDaoBizImpl implements BookDaoBiz {

    private final List<Book> books = new ArrayList<>();

    @PostConstruct
    void postConstruct() {
        final Book bookA = new Book(UUID.randomUUID(), "Astrophysics For People In Hurry", "Neil deGrasse Tyson");
        final Book bookB = new Book(UUID.randomUUID(), "Death by Black Hole", "Neil deGrasse Tyson");
        final Book bookC = new Book(UUID.randomUUID(), "Origins: Fourteen Billion Years of Cosmic Evolution", "Neil deGrasse Tyson");
        final Book bookD = new Book(UUID.randomUUID(), "Cosmos: A Personal Voyage", "Carl Sagan");
        final Book bookE = new Book(UUID.randomUUID(), "Pale Blue Dot: A Vision of the Human Future in Space", "Carl Sagan");
        final Book bookF = new Book(UUID.randomUUID(), "Origin", "Dan Brown");
        final Book bookG = new Book(UUID.randomUUID(), "Turning Points: A journey through challenges", "A.P.J. Abdul Kalam");
        final Book bookH = new Book(UUID.randomUUID(), "Angel and Demons", "Dan Brown");
        books.add(bookA);
        books.add(bookB);
        books.add(bookC);
        books.add(bookD);
        books.add(bookE);
        books.add(bookF);
        books.add(bookG);
        books.add(bookH);
    }

    @Override
    public List<Book> getAllBooks() {
        return books.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByAuthor(String author) {
        final String caseInsensitiveAuthor = author.toLowerCase();
        return books.stream().filter(book ->
                book.getAuthor().toLowerCase().contains(caseInsensitiveAuthor)).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByTitle(String titleString) {
        final String caseInsensitiveTitle = titleString.toLowerCase();
        return books.stream().filter(book ->
                book.getTitle().toLowerCase().contains(caseInsensitiveTitle)).collect(Collectors.toList());
    }

    @Override
    public Book getBookByIsbnNumber(String isbn) throws BookNotFoundException {
        final Optional<Book> book = books.stream().filter(b -> b.getIsbn().equals(UUID.fromString(isbn))).findFirst();
        if (book.isPresent()) {
            return book.get();
        }
        log.error("Book {} could not be found", isbn);
        throw new BookNotFoundException("Book with ISBN number %s could not be found", isbn);
    }
}
