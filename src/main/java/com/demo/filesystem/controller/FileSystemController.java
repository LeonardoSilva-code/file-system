package com.demo.filesystem.controller;


import com.demo.filesystem.dto.CreateDirectoryDTO;
import com.demo.filesystem.dto.FileSystemResponseDTO;
import com.demo.filesystem.entity.Directory;
import com.demo.filesystem.entity.File;
import com.demo.filesystem.exceptions.AppException;
import com.demo.filesystem.service.FileSystemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/filesystem"})
public class FileSystemController {

    private final FileSystemService fileSystemService;

    public FileSystemController(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    @PostMapping("/directory")
    public Directory createDirectory(@RequestBody CreateDirectoryDTO createDirectoryDTO) throws AppException {
        return this.fileSystemService.createDirectory(createDirectoryDTO);
    }

    @PostMapping("/file")
    public File createFile(@RequestBody CreateDirectoryDTO createDirectoryDTO) throws AppException {
        return this.fileSystemService.createFile(createDirectoryDTO);
    }
    @GetMapping("/root")
    public List<FileSystemResponseDTO> getRootDirectory(){
         return this.fileSystemService.listRoot();
    }

    @GetMapping("/directory/{directory_id}")
    public List<FileSystemResponseDTO> getRootDirectory(@PathVariable("directory_id") String directoryId){
        return this.fileSystemService.listByParentId(UUID.fromString(directoryId));
    }

}
