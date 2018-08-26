package com.hsbc.springboot.springboottraining.service;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
import com.hsbc.springboot.springboottraining.entity.FileTypeEntity;

public interface FileTypeService {
	FileTypeEntity insertFile(FileTypeEntity fileTypeEntity);
	FileTypeEntity findByFileId(long fileId);
	void deleteFile(long fileId);
}
