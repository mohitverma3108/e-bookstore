package com.assignment.bookstore.controller;

import com.assignment.bookstore.model.Book;
import com.assignment.bookstore.model.BookRequest;
import com.assignment.bookstore.model.CheckoutBooksRequest;
import com.assignment.bookstore.model.CheckoutBooksResponse;
import com.assignment.bookstore.service.BookStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/store")
@Validated
public class BookStoreController {

    @Autowired
    BookStoreService bookStoreService;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "404", description = "Searched book not found in the book store",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Generic internal server error",
                    content = @Content)})
    @GetMapping("/search-book/{id}")
    public ResponseEntity<Book> searchBook(@Parameter(description = "Id of book to be searched")
                                           @PathVariable long id) {
        return bookStoreService.searchBook(id);
    }


    @Operation(summary = "Add a new book into book store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New book added successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "400", description = "Missing request param or invalid format",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Generic internal server error",
                    content = @Content)})
    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@Parameter(description = "Details of new book to be added")
                                        @Valid @RequestBody BookRequest book) {
        return bookStoreService.addBook(book);
    }

    @Operation(summary = "Update book details by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Details updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "404", description = "Supplied book id not found in the book store",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Missing request param or invalid format",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Generic internal server error",
                    content = @Content)})
    @PutMapping("/modify-book/{id}")
    public ResponseEntity<Book> updateBookDetails(@Parameter(description = "Details of book to be updated")
                                                  @PathVariable long id, @RequestBody BookRequest book) {
        return bookStoreService.updateBookDetails(id,book);
    }

    @Operation(summary = "Remove a book from book store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book removed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))}),
            @ApiResponse(responseCode = "404", description = "Supplied book id not found in the book store",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Generic internal server error",
                    content = @Content)})
    @DeleteMapping("/remove-book/{id}")
    public ResponseEntity<HttpStatus> removeBook(@Parameter(description = "Id of book to be removed")
                                                 @PathVariable long id) {
        return bookStoreService.removeBook(id);
    }

    @Operation(summary = "Remove a book from book store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book removed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))}),
            @ApiResponse(responseCode = "404", description = "Supplied book id not found in the book store",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Generic internal server error",
                    content = @Content)})
    @GetMapping("/checkout-book")
    public ResponseEntity<CheckoutBooksResponse> checkoutAllBooks(@Parameter(description = "Checkout all books")
                                                           @RequestBody CheckoutBooksRequest checkoutBooksRequest) {
        return bookStoreService.checkoutAllBooks(checkoutBooksRequest);
    }

}
