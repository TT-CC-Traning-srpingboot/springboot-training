package com.hsbc.springboot.springboottraining.repository;

import com.hsbc.springboot.springboottraining.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository  extends JpaRepository<FolderEntity,Long> {
    /**
     * @Author Lucy
     * @Description 根据用户ID查询文件夹信息
     * @Date 23:20 2018/8/6
     * @Param userId 用户id
     * @return FolderEntity
     **/
    FolderEntity findByUserId(Long userId);
}
