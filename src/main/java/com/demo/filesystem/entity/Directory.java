package com.demo.filesystem.entity;

import com.demo.filesystem.entity.config.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "directory", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "parent_directoy_id"}))
public class Directory extends BaseEntity {
    @Column(name = "parent_directoy_id")
    UUID parentDirectoyId;

}
