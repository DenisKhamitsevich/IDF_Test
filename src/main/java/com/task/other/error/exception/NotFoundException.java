package com.task.other.error.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Indicates that desired content was not found
 */
public class NotFoundException extends ApiException {

    private static final HttpStatus HTTP_STATUS = NOT_FOUND;

    public NotFoundException(String message) {
        super(HTTP_STATUS, message);
    }

}
