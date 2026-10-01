package it.lentini.moviefinder.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException (String message) {
        super(message);
    }
}