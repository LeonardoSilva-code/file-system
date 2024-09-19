package com.demo.filesystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class CreateDirectoryDTO {

    private String name;
    private UUID parentId;
}
