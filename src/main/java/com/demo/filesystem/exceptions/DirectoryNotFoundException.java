package com.demo.filesystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DirectoryNotFoundException extends AppException {

    private static final long serialVersionUID = 1L;

    public DirectoryNotFoundException(String message) {
        super(message);
    }

    public DirectoryNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
