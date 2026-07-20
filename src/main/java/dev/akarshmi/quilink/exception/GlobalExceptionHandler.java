package dev.akarshmi.quilink.exception;

import dev.akarshmi.quilink.exception.validation.ResourceGoneException;
import dev.akarshmi.quilink.exception.validation.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {

        ApiError error = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(ResourceGoneException.class)
    public ResponseEntity<ApiError> handleResourceGone(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {

        ApiError error = ApiError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.GONE.value())
                .error(HttpStatus.GONE.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.GONE)
                .body(error);
    }
}
