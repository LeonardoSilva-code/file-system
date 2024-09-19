package com.demo.filesystem.repository;

import com.demo.filesystem.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DirectoryRepository extends JpaRepository<Directory, UUID> {

   List<Directory> findAllByParentDirectoyId(UUID parentDirectoyId);
}
