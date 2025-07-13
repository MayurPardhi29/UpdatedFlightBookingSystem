package com.mayur.bookingservice.exception;

public class InvalidFareClassException extends RuntimeException {
    public InvalidFareClassException(String message) {
        super(message);
    }
}