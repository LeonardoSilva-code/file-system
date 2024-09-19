package com.demo.filesystem.exceptions.config;

import com.demo.filesystem.dto.ErrorResponseDTO;
import com.demo.filesystem.exceptions.ApiError;
import com.demo.filesystem.exceptions.ResourceIntegrityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceIntegrityException.class)
    public ResponseEntity<?> handleResourceIntegrityException(ResourceIntegrityException ex, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApiError.class)
    public ResponseEntity<?> handleResourceIntegrityException(ApiError ex, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}