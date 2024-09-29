package com.example.gitintlog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.DuplicateKeyException; // For handling MongoDB duplicate key errors
import org.springframework.web.bind.MethodArgumentNotValidException; // For handling validation errors
import javax.validation.ConstraintViolationException; // For handling validation violations
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value()); // Updated line
        return new ResponseEntity<>(new ErrorResponse(ex.getReason(), status, LocalDateTime.now()), status);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateKeyException(DuplicateKeyException ex) {
        return new ResponseEntity<>(new ErrorResponse("Duplicate key error: " + ex.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse("Validation error: " + errorMessage, HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorResponse("Constraint violation: " + ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class) // Catch-all for any other exceptions
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorResponse {
        private String message;
        private HttpStatus status;
        private LocalDateTime timestamp;

        public ErrorResponse(String message, HttpStatus status, LocalDateTime timestamp) {
            this.message = message;
            this.status = status;
            this.timestamp = timestamp;
        }
    }
}
