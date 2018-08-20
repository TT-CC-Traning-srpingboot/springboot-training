package com.hsbc.springboot.springboottraining;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hsbc.springboot.springboottraining.controller.FileManagerController;
import com.hsbc.springboot.springboottraining.entity.FileEntity;
import com.hsbc.springboot.springboottraining.repository.FileRepository;
import com.hsbc.springboot.springboottraining.service.FileService;
/**
 * @Description test
 * @Author harry
 * @Date 2018/8/5 23:29
 * @Version 1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootTrainingApplication.class) 
public class SpringBootTrainingTest {
	 private Logger logger = LoggerFactory.getLogger(SpringBootTrainingTest.class);

	 @Autowired  
	 FileService fileService;  
	 
	 @Autowired 
	 FileManagerController fileManagerController;
	 
	 @Autowired
	 FileRepository fileRepository;
	 
	 FileEntity fileEntity=new FileEntity();
	 @Test
	 public void insertFileTest(){
		 fileService.insertFile(fileEntity);
	 }
	 
	// @Test
	 public void deletFileTest(){
		 fileService.deletFile(666);
	 }
	 
	 /*@SuppressWarnings("static-access")
	 @Test
	 public void getAuthUserTest(){
		 fileManagerController.getAuthUser();
	 }*/
	 
	 long folderId=6666;
	 int deletFlag= 456;
	 
	 //@Test
	 public void findByFolderIdTest(){
		 List<FileEntity> list = fileRepository.findByFolderIdAndDeleteFlag(folderId,deletFlag);
	 }
	// @Test
	 public void findByDeleteFlagTest(){
		 List<FileEntity> list = fileRepository.findByDeleteFlag(deletFlag);
	 }
	 @Test
	 public void findByFileIdTest(){
		 long id=6666;
		 List<FileEntity> list = (List<FileEntity>) fileRepository.findByFileId(id);
	 }
}
