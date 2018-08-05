package com.hsbc.springboot.springboottraining.repository;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author Leo
* @Description 文件管理Repository
* @Date 23:19 2018/8/1
* @since 1.0.0
**/
@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {

    
    /**
    * @Author Leo
    * @Description 查询文件夹内的文件列表
    * @Date 23:20 2018/8/1
    * @Param folderId 文件夹id
    * @Param deleteFlag 删除标记*
    * @return List
     **/
    List<FileEntity> findByFolderIdAndDeleteFlag(long folderId,int deletFlag);
    
    /**
    * @Author Leo
    * @Description 通过删除标志查询文件
    * @Date 23:43 2018/8/1
    * @Param deletFlag 删除标志
    * @return List
    **/
    List<FileEntity> findByDeleteFlag(int deletFlag);

    /**
    * @Author Leo
    * @Description 根据id查找文件
    * @Date 23:22 2018/8/1
    * @Param id 文件id
    * @return  FileEntity
    **/
    FileEntity findByFileId(long id);
    
}
