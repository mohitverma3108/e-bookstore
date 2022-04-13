package com.assignment.bookstore.util;

import com.assignment.bookstore.dto.BookDTO;
import com.assignment.bookstore.model.Book;
import com.assignment.bookstore.model.BookRequest;
import com.assignment.bookstore.model.CheckoutBooksRequest;

import java.util.Arrays;
import java.util.List;

public class BookStoreTestUtil {

    public static BookRequest buildBookRequest() {
        return BookRequest.builder()
                .name("My Book 1")
                .description("Test_description")
                .author("Test_author")
                .isbn("Test_isbn")
                .price(100.0)
                .type("others")
                .build();
    }

    public static BookDTO buildBookDTORequest(long id, long typeId) {
        return BookDTO.builder()
                .id(id)
                .name("My Book 1")
                .description("Test_description")
                .author("Test_author")
                .isbn("Test_isbn")
                .price(100.0)
                .typeId(typeId)
                .build();
    }

    public static CheckoutBooksRequest buildCheckoutBooksRequest() {
        return CheckoutBooksRequest.builder()
                .promotionCode("ABC123")
                .books(Arrays.asList(Book.builder()
                        .id(1)
                        .build(),Book.builder()
                        .id(2)
                        .build()))
                .build();
    }
}
