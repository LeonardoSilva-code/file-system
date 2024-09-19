package com.demo.filesystem.exceptions;

public class AppException extends Exception {
    private static final long serialVersionUID = 1L;
    private Throwable innerException;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable t) {
        this(message);
        this.innerException = t;
    }

    public Throwable getInnerException() {
        return innerException;
    }
}
