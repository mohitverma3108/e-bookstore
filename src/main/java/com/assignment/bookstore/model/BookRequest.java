package com.assignment.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotEmpty(message = "Book name can not be empty")
    @Size(min = 2, max = 100, message = "Book name must be between 2 and 100 characters long")
    private String name;
    private String description;
    private String author;


    @NotEmpty(message = "Book type can not be empty")
    @Size(min = 2, max = 50, message = "Book type must be between 2 and 100 characters long")
    private String type;


    @NotNull
    private Double price;
    private String isbn;
}
