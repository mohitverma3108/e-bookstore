package com.assignment.bookstore.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CheckoutBooksResponse {
    private double totalPrice;
}
