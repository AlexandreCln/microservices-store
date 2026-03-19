package fr.microservices_store.catalog_service.web.exception;

import java.net.URI;
import java.time.Instant;

import fr.microservices_store.catalog_service.domain.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Advice - Code automatically injected by the framework to intervene in the normal execution flow
@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // ProblemDetail "type" field (RFC 7807):
    // A unique identifier for this error, expressed as a URI.
    // This field is a unique identifier for the error type (not meant to be called).
    // Clients can rely on this value to programmatically handle specific errors.
    // Optionally, the URI can point to documentation describing the error.
    private static final URI NOT_FOUND_TYPE = URI.create("https://api.store.com/errors/not-found");
    private static final URI ISE_FOUND_TYPE = URI.create("https://api.store.com/errors/server-error");
    private static final String SERVICE_NAME = "catalog-service";

    // ProblemDetail - A standard JSON format for representing HTTP API errors.
    // Ensures that errors are consistent and does not reveal any information about the technologies used.
    @ExceptionHandler(Exception.class)
    ProblemDetail handleUnhandledException(Exception e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(ISE_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ProblemDetail handleProductNotFoundException(ProductNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Product Not Found");
        problemDetail.setType(NOT_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}