package com.example.library.exception;

/**
 * Thrown to indicate that a method has been passed an illegal format.
 *
 * @author Liliya Rafikova
 */
public class IncorrectFormatException extends IllegalArgumentException {

    /**
     * Constructs an <code>IncorrectFormatException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public IncorrectFormatException(String message) {
        super(message);
    }
}
