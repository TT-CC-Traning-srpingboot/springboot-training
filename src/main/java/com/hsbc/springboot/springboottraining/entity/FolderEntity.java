package com.hsbc.springboot.springboottraining.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 文件夹
 * @author Leo
 * @version 1.0.0
 */
@Entity
@Data
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /** 文件夹名 */
    private String folderName;
    /** 创建人 */
    private String createUser;
    /**创建时间 */
    private Date createDate;
    /** 人员表ID */
    private long userId;
}
