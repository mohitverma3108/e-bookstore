package com.assignment.bookstore.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ErrorResponse {
    private String errorMessage;
}
