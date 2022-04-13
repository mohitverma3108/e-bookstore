package com.assignment.bookstore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 711135094114710527L;

}
