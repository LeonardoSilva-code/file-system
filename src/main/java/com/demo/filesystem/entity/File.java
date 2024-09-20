package com.demo.filesystem.entity;

import com.demo.filesystem.entity.config.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "file", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "directoy_id"}))
public class File extends BaseEntity {

    @Column(name = "size_in_bytes")
    BigDecimal sizeInBytes;

    @Column(name = "directoy_id")
    UUID directoyId;
    String extension;
}
