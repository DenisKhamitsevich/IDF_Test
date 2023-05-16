package com.task.other.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * ApiException is superclass for customisable http exceptions
 */
public class ApiException extends RuntimeException {

    @Getter
    private HttpStatus httpStatus;

    @Getter
    private boolean customMessage;

    ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.customMessage = true;
        this.httpStatus = httpStatus;
    }
}
