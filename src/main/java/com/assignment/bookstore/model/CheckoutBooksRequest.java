package com.assignment.bookstore.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CheckoutBooksRequest {

    private String promotionCode;
    @Valid
    @NotEmpty
    private List<Book> books;
}
