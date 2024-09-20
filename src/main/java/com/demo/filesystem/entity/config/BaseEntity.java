package com.demo.filesystem.entity.config;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String name;
    @Column(name = "created_date", nullable = false, updatable = false)
    LocalDateTime createdDate;
    @Column(name = "updated_date", nullable = false)
    LocalDateTime updatedDate;

}
