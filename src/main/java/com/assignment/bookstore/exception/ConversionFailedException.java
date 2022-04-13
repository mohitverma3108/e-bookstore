package com.assignment.bookstore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ConversionFailedException extends RuntimeException {

    private static final long serialVersionUID = 328135094114710754L;
    private final String message;
}
