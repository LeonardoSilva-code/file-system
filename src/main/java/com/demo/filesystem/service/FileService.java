package com.demo.filesystem.service;

import com.demo.filesystem.dto.CreateDirectoryDTO;
import com.demo.filesystem.entity.Directory;
import com.demo.filesystem.entity.File;
import com.demo.filesystem.exceptions.ApiError;
import com.demo.filesystem.exceptions.AppException;
import com.demo.filesystem.exceptions.ResourceIntegrityException;
import com.demo.filesystem.repository.FileRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final String UniqueNameViolationErrorMesage = "could not execute statement [Unique index or primary key violation:";


    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    protected File createFile(CreateDirectoryDTO input) throws AppException {
        try {
            File fileToSave = new File();
            fileToSave.setName(input.getName());
            fileToSave.setCreatedDate(LocalDateTime.now());
            fileToSave.setUpdatedDate(LocalDateTime.now());
            fileToSave.setExtension("png");
            fileToSave.setSizeInBytes(new BigDecimal(10));
            return this.fileRepository.save(fileToSave);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains(UniqueNameViolationErrorMesage))
                throw new ResourceIntegrityException("A directory with the name '" + input.getName() + "' already exists.");
            throw new ApiError(e.getMessage());
        }
    }

    protected List<File> listDirectoriesByParentId(UUID parentId){
        return this.fileRepository.findAllByDirectoyId(parentId);
    }

    protected List<File> listFilesOnRoot(){
        return this.fileRepository.findAllByDirectoyId(null);
    }
}
