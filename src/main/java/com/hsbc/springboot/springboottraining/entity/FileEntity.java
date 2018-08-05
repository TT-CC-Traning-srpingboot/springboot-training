package com.hsbc.springboot.springboottraining.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 文件表
 * @author Leo
 * * @version 1.0.0
 */
@Entity
@Data
@Table(name="t_file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long fileId;
    /**
     * 所属文件夹ID
     */
    private long folderId;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 上传人
     */
    private long userId;

     /**
      * 人员名称
     */
    private long uploadUser;
    /**
     * 上传时间
     */
    private Date uploadDate;
    /**
     * 删除标志
     */
    private int deleteFlag;


}
