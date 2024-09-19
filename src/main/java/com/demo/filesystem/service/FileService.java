package com.demo.filesystem.service;

import com.demo.filesystem.dto.CreateFileDTO;
import com.demo.filesystem.entity.File;
import com.demo.filesystem.exceptions.ApiError;
import com.demo.filesystem.exceptions.AppException;
import com.demo.filesystem.exceptions.FileNotFoundException;
import com.demo.filesystem.exceptions.ResourceIntegrityException;
import com.demo.filesystem.repository.FileRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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

    protected File createFile(CreateFileDTO input) throws AppException {
        try {
            File fileToSave = new File();
            fileToSave.setName(input.getName());
            fileToSave.setCreatedDate(LocalDateTime.now());
            fileToSave.setUpdatedDate(LocalDateTime.now());
            fileToSave.setExtension(input.getExtension());
            fileToSave.setSizeInBytes(input.getSizeInBytes());
            return this.fileRepository.save(fileToSave);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains(UniqueNameViolationErrorMesage))
                throw new ResourceIntegrityException("A directory with the name '" + input.getName() + "' already exists.");
            throw new ApiError(e.getMessage());
        }
    }

    protected List<File> listFilesByParentId(UUID parentId){
        return this.fileRepository.findAllByDirectoyId(parentId);
    }

    protected List<File> listFilesOnRoot(){
        return this.fileRepository.findAllByDirectoyId(null);
    }

    protected File saveFile(File file){
        return this.fileRepository.save(file);
    }

    protected File getById(UUID id) throws AppException{
        try {
            return this.fileRepository.getOne(id);
        }catch (Exception e){
            throw new FileNotFoundException("A file with the id: " + id + " was not found.");
        }
    }

    protected void deleteFile(UUID fileId){
       this.fileRepository.deleteById(fileId);
    }
}
