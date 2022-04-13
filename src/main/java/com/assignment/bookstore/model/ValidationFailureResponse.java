package com.assignment.bookstore.model;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ValidationFailureResponse {
    private String message;
    private List<ValidationError> fieldErrors;
}
