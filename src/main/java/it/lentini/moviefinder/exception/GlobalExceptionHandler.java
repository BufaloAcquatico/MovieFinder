package it.lentini.moviefinder.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleResourceNotFoundException(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ) {
        return ApiErrorResponse.of(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        return ApiErrorResponse.withFieldErrors(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                request.getRequestURI(),
                fieldErrors
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleUnreadableMessageException(
            HttpMessageNotReadableException exception,
            HttpServletRequest request
    ) {
        return ApiErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                "Malformed request body",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiErrorResponse handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {

        return ApiErrorResponse.of(
                HttpStatus.METHOD_NOT_ALLOWED,
                String.format("HTTP method %s is not supported for this resource. Allowed methods: %s",
                                ex.getMethod(),
                                Arrays.toString( ex.getSupportedMethods() ).replace("[","").replace("]","")) ,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleBadCredentialsException(
            BadCredentialsException exception,
            HttpServletRequest request
    ) {
        return ApiErrorResponse.of(
                HttpStatus.UNAUTHORIZED,
                "Invalid credentials",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleEmailAlreadyExistsException(
            EmailAlreadyExistsException exception,
            HttpServletRequest request
    ) {
        return ApiErrorResponse.of(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Void> handleDuplicate(
            DataIntegrityViolationException e
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}