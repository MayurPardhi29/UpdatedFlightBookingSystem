package com.mayur.fareservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FareServiceException extends RuntimeException {
    public FareServiceException(String message) {
        super(message);
    }

    public FareServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}