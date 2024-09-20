package com.demo.filesystem.service;

import com.demo.filesystem.dto.CreateDirectoryDTO;
import com.demo.filesystem.dto.CreateFileDTO;
import com.demo.filesystem.dto.FileSystemResponseDTO;
import com.demo.filesystem.dto.FileSystemType;
import com.demo.filesystem.entity.Directory;
import com.demo.filesystem.entity.File;
import com.demo.filesystem.exceptions.AppException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileSystemService {

    private final DirectoryService directoryService;
    private final FileService fileService;
    public FileSystemService(DirectoryService directoryService, FileService fileService) {
        this.directoryService = directoryService;
        this.fileService = fileService;
    }

    public Directory createDirectory(CreateDirectoryDTO input) throws AppException {
        return this.directoryService.createDirectory(input);
    }

    public File createFile(CreateFileDTO input) throws AppException {
        return this.fileService.createFile(input);
    }

    public Directory updateDirectory(CreateDirectoryDTO input, UUID directoryID) throws AppException {
        Directory directory = this.directoryService.getById(directoryID);
        directory.setName(input.getName());
        directory.setParentDirectoyId(input.getParentId());
        directory.setUpdatedDate(LocalDateTime.now());
        return this.directoryService.saveDirectory(directory);
    }

    public File updateFile(CreateFileDTO input, UUID fileID) throws AppException {
        File file = this.fileService.getById(fileID);
        file.setName(input.getName());
        file.setDirectoyId(input.getParentId());
        file.setSizeInBytes(input.getSizeInBytes());
        file.setExtension(input.getExtension());
        file.setUpdatedDate(LocalDateTime.now());
        return this.fileService.saveFile(file);
    }

    @Transactional
    public void deleteDirectory(UUID directoryId){
        List<File> childrenFiles = this.fileService.listFilesByParentId(directoryId);
        if(!childrenFiles.isEmpty()){
            childrenFiles.forEach(f -> this.deleteFile(f.getId()));
        }
        this.directoryService.deleteDirectory(directoryId);
    }

    public void deleteFile(UUID fileId){
        this.fileService.deleteFile(fileId);
    }

    public List<FileSystemResponseDTO> listByParentId(UUID parentId){
        List<Directory> directories = this.directoryService.listDirectoriesByParentId(parentId);
        List<File> files = this.fileService.listFilesByParentId(parentId);
        return buildFileSystemResponse(directories, files);
    }


    public List<FileSystemResponseDTO> listRoot(){
        List<Directory> rootDirectories = this.directoryService.listDirectoriesOnRoot();
        List<File> rootFiles = this.fileService.listFilesOnRoot();
        return buildFileSystemResponse(rootDirectories, rootFiles);
    }

    private List<FileSystemResponseDTO> buildFileSystemResponse(List<Directory> directories, List<File> files){
        List<FileSystemResponseDTO> responseDTOs = new ArrayList<>();
        List<FileSystemResponseDTO> directoryDTOs = directories.stream()
                .map(directory -> {
                    FileSystemResponseDTO dto = new FileSystemResponseDTO();
                    dto.setName(directory.getName());
                    dto.setCreatedDate(directory.getCreatedDate());
                    dto.setUpdatedDate(directory.getUpdatedDate());
                    dto.setType(FileSystemType.FOLDER);
                    dto.setId(directory.getId());
                    return dto;
                })
                .collect(Collectors.toList());

        List<FileSystemResponseDTO> fileDTOs = files.stream()
                .map(file -> {
                    FileSystemResponseDTO dto = new FileSystemResponseDTO();
                    dto.setName(file.getName());
                    dto.setCreatedDate(file.getCreatedDate());
                    dto.setUpdatedDate(file.getUpdatedDate());
                    dto.setType(FileSystemType.FILE);
                    dto.setExtension(file.getExtension());
                    dto.setSizeInBytes(file.getSizeInBytes());
                    dto.setId(file.getId());
                    return dto;
                })
                .collect(Collectors.toList());

        responseDTOs.addAll(directoryDTOs);
        responseDTOs.addAll(fileDTOs);

        return responseDTOs;
    }

}
