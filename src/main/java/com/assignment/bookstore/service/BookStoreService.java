package com.assignment.bookstore.service;

import com.assignment.bookstore.model.Book;
import com.assignment.bookstore.model.BookRequest;
import com.assignment.bookstore.model.CheckoutBooksRequest;
import com.assignment.bookstore.model.CheckoutBooksResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface BookStoreService {

    public ResponseEntity<Book> searchBook(long bookId);
    public ResponseEntity<Book> addBook(BookRequest book);
    public ResponseEntity<Book> updateBookDetails(long bookId, BookRequest book);
    public ResponseEntity<HttpStatus> removeBook(long bookId);
    public ResponseEntity<CheckoutBooksResponse> checkoutAllBooks(CheckoutBooksRequest checkoutBooks);
}
