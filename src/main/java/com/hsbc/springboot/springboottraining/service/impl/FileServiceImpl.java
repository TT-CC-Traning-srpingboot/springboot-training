package com.hsbc.springboot.springboottraining.service.impl;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
import com.hsbc.springboot.springboottraining.repository.FileRepository;
import com.hsbc.springboot.springboottraining.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 文件管理Service
 * @Author Leo
 * @Date 2018/8/1 23:29
 * @Version 1.0.0
 **/
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileEntity insertFile(FileEntity fileEntity) {
        FileEntity saveFileEntity = fileRepository.save(fileEntity);
        return saveFileEntity;
    }

    @Override
    public void deletFile(long fileId) {
         fileRepository.delete(fileId);
    }

    @Override
    public List<FileEntity> findByFolderIdAndDeleteFlag(long folderId, int deleteFlag) {
        if(folderId ==0){
            return fileRepository.findByDeleteFlag(deleteFlag);
        }else{
            return fileRepository.findByFolderIdAndDeleteFlag(folderId,deleteFlag);
        }
    }

    @Override
    public FileEntity findById(long id) {
        return fileRepository.findById(id);
    }
}
