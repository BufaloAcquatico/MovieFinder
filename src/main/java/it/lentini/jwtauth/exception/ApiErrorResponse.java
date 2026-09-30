package it.lentini.jwtauth.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

public record ApiErrorResponse(
        int status,
        String error,
        String message,
        String path,
        Instant timestamp,
        Map<String, String> fieldErrors
) {
    public static ApiErrorResponse of(
            HttpStatus status,
            String message,
            String path
    ) {
        return new ApiErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                Instant.now(),
                Map.of()
        );
    }

    public static ApiErrorResponse withFieldErrors(
            HttpStatus status,
            String message,
            String path,
            Map<String, String> fieldErrors
    ) {
        return new ApiErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                Instant.now(),
                fieldErrors
        );
    }
}
