package com.demo.filesystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class FileSystemResponseDTO {
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private FileSystemType type;
    private String extension;
    private BigDecimal sizeInBytes;
}
