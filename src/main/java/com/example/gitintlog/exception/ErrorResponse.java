package com.example.gitintlog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;

    // No-args constructor for flexibility if needed
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now(); // Initialize timestamp if needed
    }
}
