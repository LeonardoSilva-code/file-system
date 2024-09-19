package com.demo.filesystem.service;

import com.demo.filesystem.dto.CreateDirectoryDTO;
import com.demo.filesystem.entity.Directory;
import com.demo.filesystem.exceptions.ApiError;
import com.demo.filesystem.exceptions.AppException;
import com.demo.filesystem.exceptions.DirectoryNotFoundException;
import com.demo.filesystem.exceptions.ResourceIntegrityException;
import com.demo.filesystem.repository.DirectoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DirectoryService {
    private final DirectoryRepository directoryRepository;
    private final String UniqueNameViolationErrorMesage = "could not execute statement [Unique index or primary key violation:";
    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    protected Directory createDirectory(CreateDirectoryDTO input) throws AppException {
        try {
            Directory directoryToSave = new Directory();
            directoryToSave.setName(input.getName());
            directoryToSave.setParentDirectoyId(input.getParentId());
            directoryToSave.setUpdatedDate(LocalDateTime.now());
            directoryToSave.setCreatedDate(LocalDateTime.now());
            return this.directoryRepository.save(directoryToSave);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains(UniqueNameViolationErrorMesage))
                throw new ResourceIntegrityException("A directory with the name '" + input.getName() + "' already exists.");
            throw new ApiError(e.getMessage());
        }
    }

    protected Directory saveDirectory(Directory directory){
        return this.directoryRepository.save(directory);
    }


    protected Directory getById(UUID id) throws AppException{
        try {
            return this.directoryRepository.getOne(id);
        }catch (Exception e){
            throw new DirectoryNotFoundException("A directory with the id: " + id + " was not found.");
        }
    }

    protected List<Directory> listDirectoriesByParentId(UUID parentId){
        return this.directoryRepository.findAllByParentDirectoyId(parentId);
    }

    protected List<Directory> listDirectoriesOnRoot(){
        return this.directoryRepository.findAllByParentDirectoyId(null);
    }

    protected void deleteDirectory(UUID id){
        List<Directory> childrenDirectories = this.listDirectoriesByParentId(id);
        if(!childrenDirectories.isEmpty()){
            this.directoryRepository.deleteAll(childrenDirectories);
        }
        this.directoryRepository.deleteById(id);
    }

}
