package com.hsbc.springboot.springboottraining.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name = "t_file_folder")
public class FileTypeEntity {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long fileId;
	    /**
	     * 所属文件夹ID
	     */
	    private long folderId;
	    
	    /**
	     * 分类文件夹名
	     */
	    private String  fileFolderName;
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
	    private String uploadUser;
	    /**
	     * 上传时间
	     */
	    private Date uploadDate;
	    /**
	     * 删除标志(0代表正常，1代表删除)
	     */
	    private int deleteFlag;
}
