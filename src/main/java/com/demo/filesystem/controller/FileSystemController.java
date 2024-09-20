package com.demo.filesystem.controller;


import com.demo.filesystem.dto.BreadcrumbResponseDTO;
import com.demo.filesystem.dto.CreateDirectoryDTO;
import com.demo.filesystem.dto.CreateFileDTO;
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

    @GetMapping("/root")
    public List<FileSystemResponseDTO> getRootDirectory(){
        return this.fileSystemService.listRoot();
    }

    @GetMapping("/directory/{directory_id}")
    public List<FileSystemResponseDTO> getRootDirectory(@PathVariable("directory_id") String directoryId){
        return this.fileSystemService.listByParentId(UUID.fromString(directoryId));
    }

    @GetMapping("/breadcrumb/{directory_id}")
    public List<BreadcrumbResponseDTO> getBreadcrumbforDirectory(@PathVariable("directory_id") String directoryId) throws AppException {
        return this.fileSystemService.getBreadcrumbforDirectory(UUID.fromString(directoryId));
    }

    @PostMapping("/directory")
    public Directory createDirectory(@RequestBody CreateDirectoryDTO createDirectoryDTO) throws AppException {
        return this.fileSystemService.createDirectory(createDirectoryDTO);
    }

    @PostMapping("/file")
    public File createFile(@RequestBody CreateFileDTO createFileDTO) throws AppException {
        return this.fileSystemService.createFile(createFileDTO);
    }

    @PatchMapping("/directory/{directory_id}")
    public Directory updateDirectory(@RequestBody CreateDirectoryDTO createDirectoryDTO, @PathVariable("directory_id") String directoryId) throws AppException {
        return this.fileSystemService.updateDirectory(createDirectoryDTO, UUID.fromString(directoryId));
    }

    @PatchMapping("/file/{file_id}")
    public File updateDirectory(@RequestBody CreateFileDTO createFileDTO, @PathVariable("file_id") String fileId) throws AppException {
        return this.fileSystemService.updateFile(createFileDTO, UUID.fromString(fileId));
    }

    @DeleteMapping("/directory/{directory_id}")
    public void deleteDirectory(@PathVariable("directory_id") String directoryId){
        this.fileSystemService.deleteDirectory(UUID.fromString(directoryId));
    }

    @DeleteMapping("/file/{file_id}")
    public void deleteFile(@PathVariable("file_id") String fileId){
        this.fileSystemService.deleteFile(UUID.fromString(fileId));
    }
}
