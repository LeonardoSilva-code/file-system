package com.demo.filesystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
public class CreateFileDTO {
    private String name;
    private UUID parentId;
    BigDecimal sizeInBytes;
    String extension;
}
