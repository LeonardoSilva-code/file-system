package com.demo.filesystem.repository;

import com.demo.filesystem.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {

    List<File> findAllByDirectoyId(UUID directoyId);
}
