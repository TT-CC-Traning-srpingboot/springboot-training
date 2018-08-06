package com.hsbc.springboot.springboottraining.service;

import com.hsbc.springboot.springboottraining.entity.FileEntity;

import java.util.List;


public interface FileService {

    FileEntity insertFile(FileEntity fileEntity);

    void deletFile(long fileId);

    List<FileEntity> findByFolderIdAndDeleteFlag(long folderId, int deleteFlag);

    FileEntity findByFileId(long fileId);
}
