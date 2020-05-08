package com.hsahu.application.controller;

import com.hsahu.application.biz.BookDaoBizImpl;
import com.hsahu.application.dto.Book;
import com.hsahu.application.exception.BookNotFoundException;
import com.hsahu.application.exception.InvalidQueryException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDaoBizImpl bookDaoBiz;

    @GetMapping
    List<Book> listAllBooks() {
        return bookDaoBiz.getAllBooks();
    }

    @GetMapping("/search/author/{author}")
    List<Book> searchBookByAuthor(@PathVariable(value = "author") String author) throws InvalidQueryException {
        if (Strings.isBlank(author)) {
            throw new InvalidQueryException("Author name can not be empty.");
        }
        return bookDaoBiz.searchBookByAuthor(author);
    }

    @GetMapping("/search/title/{title}")
    List<Book> searchBookByTitle(@PathVariable(value = "title") String title) throws InvalidQueryException {
        if (Strings.isBlank(title)) {
            throw new InvalidQueryException("Book title can not be empty.");
        }
        return bookDaoBiz.searchBookByTitle(title);
    }

    @GetMapping("/isbn/{isbn}")
    Book getBookByIsbn(@PathVariable(value = "isbn") String isbn) throws InvalidQueryException, BookNotFoundException {
        if (Strings.isBlank(isbn)) {
            throw new InvalidQueryException("Book ISBN number can not be empty.");
        }
        return bookDaoBiz.getBookByIsbnNumber(isbn);
    }
}
