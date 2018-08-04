package com.hsbc.springboot.springboottraining.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * 文件夹
 * @author Leo
 * @version 1.0.0
 */
@Entity
@Data
@Table(name = "t_folder")
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long folderId;
    /** 文件夹名 */
    private String folderName;
    /** 创建人 */
    private String createUser;
    /**创建时间 */
    private Date createDate;
    /** 人员表ID */
    private long userId;
}
