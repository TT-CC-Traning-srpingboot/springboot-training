package com.hsbc.springboot.springboottraining;


import org.assertj.core.groups.FieldsOrPropertiesExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
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
	 FileEntity fileEntity=new FileEntity();
	 @Test
	 public void insertFileTest(){
		 fileService.insertFile(fileEntity);
	 }
	 
	// @Test
	 public void deletFileTest(){
		 fileService.deletFile(666);
	 }

}
