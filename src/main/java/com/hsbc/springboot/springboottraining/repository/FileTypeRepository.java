package com.hsbc.springboot.springboottraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsbc.springboot.springboottraining.entity.FileTypeEntity;
/**
* 
* @Description 文件管理Repository
* @Author Harry
* @since 1.0.0
**/
@Repository
public interface FileTypeRepository extends JpaRepository<FileTypeEntity,Long> {
	
}
