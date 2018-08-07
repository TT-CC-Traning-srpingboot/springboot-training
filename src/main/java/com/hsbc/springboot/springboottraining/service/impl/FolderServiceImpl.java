package com.hsbc.springboot.springboottraining.service.impl;

import com.hsbc.springboot.springboottraining.entity.FolderEntity;
import com.hsbc.springboot.springboottraining.repository.FolderRepository;
import com.hsbc.springboot.springboottraining.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Lucy
 * @Description 文件夹管理
 * @Date 23:12 2018/8/5
 **/
@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository  folderRepository;

    @Override
    public FolderEntity insertFolder(FolderEntity folderEntity) {
        FolderEntity saveFileEntity = folderRepository.save(folderEntity);
        return saveFileEntity;
    }
}
