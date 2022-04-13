package com.assignment.bookstore.dto;

import com.assignment.bookstore.model.BookRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutBooksDTO {

    private String promotionCode;
    private List<BookRequest> books;
}
