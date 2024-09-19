package com.demo.filesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {
    private int statusCode;
    private String message;
}
