package com.hsahu.application.biz;

import com.hsahu.application.dto.Book;
import com.hsahu.application.exception.BookNotFoundException;

import java.util.List;

public interface BookDaoBiz {
    List<Book> getAllBooks();
    List<Book> searchBookByAuthor(final String author);
    List<Book> searchBookByTitle(final String titleString);
    Book getBookByIsbnNumber(final String isbn) throws BookNotFoundException;
}
