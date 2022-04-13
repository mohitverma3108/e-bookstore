package com.assignment.bookstore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookStoreCustomException extends RuntimeException {
    private static final long serialVersionUID = 824535094114711209L;
    private final String message;
}
