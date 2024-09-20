package com.demo.filesystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BreadcrumbResponseDTO {

    String Id;
    String name;
    int position;
}