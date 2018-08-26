package com.hsbc.springboot.springboottraining.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsbc.springboot.springboottraining.entity.FileTypeEntity;
import com.hsbc.springboot.springboottraining.repository.FileTypeRepository;
import com.hsbc.springboot.springboottraining.service.FileTypeService;
/**
 * @Description 文件管理Service
 * @Author Harry
 * @Version 1.0.0
 **/
@Service
public class FileTypeServiceImpl implements FileTypeService{
	 @Autowired
	 private FileTypeRepository fileTypeRepository;
	 
	@Override
	public FileTypeEntity insertFile(FileTypeEntity fileTypeEntity) {
		FileTypeEntity saveFileEntity = fileTypeRepository.save(fileTypeEntity);
	     return saveFileEntity;
	}

}
