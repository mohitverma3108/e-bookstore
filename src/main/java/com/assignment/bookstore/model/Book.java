package com.assignment.bookstore.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonPropertyOrder({ "id", "name", "description", "author", "type", "price", "isbn" })
public class Book {
    @NotNull
    private long id;
    private String name;
    private String description;
    private String author;
    private String type;
    private Double price;
    private String isbn;
}