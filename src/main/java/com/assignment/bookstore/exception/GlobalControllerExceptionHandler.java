package com.assignment.bookstore.exception;

import com.assignment.bookstore.model.ErrorResponse;
import com.assignment.bookstore.model.ValidationError;
import com.assignment.bookstore.model.ValidationFailureResponse;
import com.assignment.bookstore.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(basePackages = {"com.assignment.bookstore"})
public class GlobalControllerExceptionHandler {

    static Logger LOG = LogManager.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConversion(RuntimeException ex) {
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleBookNotFound(RuntimeException ex) {
        LOG.error(Constants.BOOK_NOT_FOUND_ERROR_MESSAGE);
        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(Constants.BOOK_NOT_FOUND_ERROR_MESSAGE).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookStoreCustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorResponse> handleBookStoreCustomException(BookStoreCustomException ex) {
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationFailureResponse> validationError(MethodArgumentNotValidException ex) {
        LOG.error(ex.getMessage());
        ValidationFailureResponse response = ValidationFailureResponse.builder()
                .message("Validation Failed")
                .build();
        List<ValidationError> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorList.add(ValidationError.builder()
                    .fieldName(((FieldError) error).getField())
                    .errorMessage(error.getDefaultMessage())
                    .build());
        });
        response.setFieldErrors(errorList);
        return new ResponseEntity<ValidationFailureResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorResponse> handleException(Throwable ex) {
        LOG.error(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
