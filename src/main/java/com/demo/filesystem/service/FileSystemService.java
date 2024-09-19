package com.demo.filesystem.service;

import com.demo.filesystem.dto.CreateDirectoryDTO;
import com.demo.filesystem.dto.FileSystemResponseDTO;
import com.demo.filesystem.dto.FileSystemType;
import com.demo.filesystem.entity.Directory;
import com.demo.filesystem.entity.File;
import com.demo.filesystem.exceptions.AppException;
import org.springframework.stereotype.Service;

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

    public File createFile(CreateDirectoryDTO input) throws AppException {
        return this.fileService.createFile(input);
    }

    public List<FileSystemResponseDTO> listByParentId(UUID parentId){
        List<Directory> directories = this.directoryService.listDirectoriesByParentId(parentId);
        List<File> files = this.fileService.listDirectoriesByParentId(parentId);
        List<FileSystemResponseDTO> responseDTOs = new ArrayList<>();
        List<FileSystemResponseDTO> directoryDTOs = directories.stream()
                .map(directory -> {
                    FileSystemResponseDTO dto = new FileSystemResponseDTO();
                    dto.setName(directory.getName());
                    dto.setCreatedDate(directory.getCreatedDate());
                    dto.setUpdatedDate(directory.getUpdatedDate());
                    dto.setType(FileSystemType.FOLDER);
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
                    return dto;
                })
                .collect(Collectors.toList());

        responseDTOs.addAll(directoryDTOs);
        responseDTOs.addAll(fileDTOs);

        return responseDTOs;
    }


    public List<FileSystemResponseDTO> listRoot(){
        List<Directory> rootDirectories = this.directoryService.listDirectoriesOnRoot();
        List<File> rootFiles = this.fileService.listFilesOnRoot();
        List<FileSystemResponseDTO> responseDTOs = new ArrayList<>();
        List<FileSystemResponseDTO> directoryDTOs = rootDirectories.stream()
                .map(directory -> {
                    FileSystemResponseDTO dto = new FileSystemResponseDTO();
                    dto.setName(directory.getName());
                    dto.setCreatedDate(directory.getCreatedDate());
                    dto.setUpdatedDate(directory.getUpdatedDate());
                    dto.setType(FileSystemType.FOLDER);
                    return dto;
                })
                .collect(Collectors.toList());

        List<FileSystemResponseDTO> fileDTOs = rootFiles.stream()
                .map(file -> {
                    FileSystemResponseDTO dto = new FileSystemResponseDTO();
                    dto.setName(file.getName());
                    dto.setCreatedDate(file.getCreatedDate());
                    dto.setUpdatedDate(file.getUpdatedDate());
                    dto.setType(FileSystemType.FILE);
                    dto.setExtension(file.getExtension());
                    dto.setSizeInBytes(file.getSizeInBytes());
                    return dto;
                })
                .collect(Collectors.toList());

        responseDTOs.addAll(directoryDTOs);
        responseDTOs.addAll(fileDTOs);

        return responseDTOs;
    }

}
