package com.hsbc.springboot.springboottraining.controller;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
import com.hsbc.springboot.springboottraining.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件查看
 * @author Rarer
 * * @version 1.0.0
 */
@RestController

public class FileRarerController {

    @Autowired
    private  FileService fileService;


    /**
     * 获取目录下所有文件
     * @return
     */

    @RequestMapping("/file")
    public ResponseEntity<List<FileEntity>> getAllFie(){
        List<FileEntity> files= fileService.findAll();

        return new ResponseEntity<List<FileEntity>>(files, HttpStatus.OK);
    }

    @RequestMapping("/file/{fileId}")
    public ResponseEntity<FileEntity> gelFileByFileId(@PathVariable() long fileId){
        FileEntity file= fileService.findByFileId(fileId);

        return new ResponseEntity<FileEntity>(file, HttpStatus.OK);
    }

    @RequestMapping(value = "/file/{fileId}",method = RequestMethod.DELETE)
    public ResponseEntity<> delFile(@PathVariable() long fileId){
         fileService.deletFile(fileId);
        return  new ResponseEntity<>( HttpStatus.OK);
    }

}
