package dev.akarshmi.quilink.exception;

import dev.akarshmi.quilink.exception.validation.ResourceGoneException;
import dev.akarshmi.quilink.exception.validation.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final String notFoundHtml;
    private final String goneHtml;

    public GlobalExceptionHandler() throws IOException {
        this.notFoundHtml = loadTemplate("templates/error/not-found.html");
        this.goneHtml = loadTemplate("templates/error/gone.html");
    }

    private String loadTemplate(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        String html = notFoundHtml.replace(
                "<div class=\"code\">go.akarshmi.dev/abc123</div>",
                "<div class=\"code\">" + ex.getMessage() + "</div>"
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.TEXT_HTML)
                .body(notFoundHtml);
    }

    @ExceptionHandler(ResourceGoneException.class)
    public ResponseEntity<String> handleResourceGone(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        String html = goneHtml.replace(
                "<div class=\"code\">go.akarshmi.dev/abc123</div>",
                "<div class=\"code\">" + ex.getMessage() + "</div>"
        );


        return ResponseEntity
                .status(HttpStatus.GONE)
                .contentType(MediaType.TEXT_HTML)
                .body(goneHtml);
    }
}
