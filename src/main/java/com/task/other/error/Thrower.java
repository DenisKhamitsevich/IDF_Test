package com.task.other.error;

import com.task.other.error.exception.NotFoundException;

/**
 * Class for throwing customisable http exceptions
 */
public class Thrower {

    /**
     * Throw {@code NotFoundException} with custom message
     * if the expression result is {@code true}
     *
     * @param expression a boolean expression
     * @param message    the exception message
     * @throws NotFoundException if expression is {@code true}
     */
    public static void notFound(boolean expression, String message) {
        if (expression) {
            throw new NotFoundException(message);
        }
    }
}

