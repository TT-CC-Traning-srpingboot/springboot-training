package com.hsbc.springboot.springboottraining.service;

import com.hsbc.springboot.springboottraining.entity.FolderEntity;

/**
 * @Author Lucy
 * @Description 文件夹管理
 * @Date 23:12 2018/8/5
 **/
public interface FolderService {

    //保存文件夹信息
    FolderEntity insertFolder(FolderEntity folderEntity);
}
