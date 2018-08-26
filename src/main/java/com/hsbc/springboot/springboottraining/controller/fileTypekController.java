package com.hsbc.springboot.springboottraining.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
import com.hsbc.springboot.springboottraining.entity.FileTypeEntity;
import com.hsbc.springboot.springboottraining.entity.FolderEntity;
import com.hsbc.springboot.springboottraining.entity.UserEntity;
import com.hsbc.springboot.springboottraining.service.FileService;
import com.hsbc.springboot.springboottraining.service.FileTypeService;
import com.hsbc.springboot.springboottraining.service.FolderService;
import com.hsbc.springboot.springboottraining.service.UserService;

/**
 * 对文件进行分类
 * @author Harry
 * * @version 1.0.0
 */
@Controller
@RequestMapping("/fileFolder")
public class fileTypekController {
	 private static final Logger log = LoggerFactory.getLogger(fileTypekController.class);
	 
	    @Autowired
	    private UserService userService;

	    @Autowired
	    private FolderService folderService;
	    
	    @Autowired
	    private FileTypeService fileTypeService;
	    
	    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>(); 

	  
	    @RequestMapping(value="/upload", method = RequestMethod.POST)
	    @ResponseBody
	    public String upload(@RequestParam("file") MultipartFile file) {
	        try {
	            if (file.isEmpty()) {
	                return "文件为空";
	            }
	            //调用获取当前用户
	            String currentUsername = getAuthUser();
	            // 获取原始文件名
	            String origin_Name = file.getOriginalFilename();
	            log.info("上传的文件名为：" + origin_Name);
	            // 获取文件名
	            String file_name = origin_Name.substring(origin_Name.lastIndexOf("\\")+1);
	            log.info("文件为：" + file_name);
	            // 获取文件的后缀名
	            String suffixName = origin_Name.substring(origin_Name.lastIndexOf("."));
	            log.info("文件的后缀名为：" + suffixName);
	            getAllFileType();
	            String filePath = this.getClass().getClassLoader().getResource("").getPath();
	            
	            String path = "";
	            
	            if("" != suffixName || null != suffixName ){
	            	if(FILE_TYPE_MAP.containsKey(suffixName)){
	            		path = filePath + "\\uploadfile\\"+FILE_TYPE_MAP.get(suffixName) + currentUsername + "\\" + file_name;
	            	}else {
	            		path = filePath + "\\uploadfile\\other\\"+ currentUsername + "\\" + file_name;
	     	           
					}
	            	
	            }
	             
	            File fileDir = new File(path);
	            if (!fileDir.getParentFile().exists()) {// 判断/download目录是否存在
	                fileDir.getParentFile().mkdirs();// 创建目录
	            }

	            file.transferTo(fileDir);// 文件写入
	       
	            String fileSizeString = "";
	            
	            fileSize(file,fileSizeString);
	            long fileSize = file.getSize();
	           
	            //根据用户名查询用户信息
	            UserEntity userEntity = userService.findByUserName(currentUsername);
	            //保存文件夹信息
	            if(userEntity != null ){
	                FolderEntity folderEntity = folderService.findByUserId(userEntity.getUserId());
	                if(folderEntity == null ){
	                    folderEntity = new FolderEntity();
	                    folderEntity.setFolderName(currentUsername);
	                    folderEntity.setCreateUser(currentUsername);
	                    folderEntity.setCreateDate(new Date());
	                    folderEntity.setUserId(userEntity.getUserId());
	                    folderService.insertFolder(folderEntity);
	                }
	                //保存文件数据到数据库
	                FileTypeEntity fileTypeEntity = new FileTypeEntity();
	                fileTypeEntity.setFileName(file_name);
	                fileTypeEntity.setFolderId(folderEntity.getFolderId());
	                fileTypeEntity.setFilePath(filePath);
	                fileTypeEntity.setFileSize(fileSizeString);
	                fileTypeEntity.setFileType(suffixName);
	                fileTypeEntity.setUserId(userEntity.getUserId());
	                fileTypeEntity.setUploadUser(userEntity.getUserName());
	                fileTypeEntity.setUploadDate(new Date());
	                fileTypeEntity.setDeleteFlag(0);
	                fileTypeService.insertFile(fileTypeEntity);
	            }



	            return "上传成功";
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "上传失败";
	    }
	    
	    /**
	     * 查找文件
	     * 
	     */
	    @RequestMapping("/file/{fileId}")
	    public ResponseEntity<FileTypeEntity> gelFileByFileFolderId(@PathVariable() long fileId){
	    	FileTypeEntity file= fileTypeService.findByFileId(fileId);
	        return new ResponseEntity<FileTypeEntity>(file, HttpStatus.OK);
	    }
	    
	    /**
	     * 删除文件
	     * 
	     */
	    @RequestMapping(value = "/file/{fileId}",method = RequestMethod.DELETE)
	    public void deleteFile(@PathVariable() long fileId){
	    	fileTypeService.deleteFile(fileId);
	    }
	    
	    /**
	     * 将文件类型分装到map中
	     * 
	     */             
	    private  void getAllFileType(){       
	           FILE_TYPE_MAP.put("jpg", "filejpg");        
	           FILE_TYPE_MAP.put("png", "filepng");        
	           FILE_TYPE_MAP.put("gif", "filegif");           
	           FILE_TYPE_MAP.put("html", "filehtml");       
	           FILE_TYPE_MAP.put("xml", "filexml");        
	           FILE_TYPE_MAP.put("doc", "filedoc");    
	           FILE_TYPE_MAP.put("xlsx", "filelsx");      
	           FILE_TYPE_MAP.put("xls", "filelsx");       
	           FILE_TYPE_MAP.put("ppt", "fileppt");   
	           FILE_TYPE_MAP.put("pdf", "filepdf");         
	    }     
	    
	    /**
	     * 文件大小转换
	     */ 
	    public void  fileSize(MultipartFile file,String fileSizeString) {
	    	  DecimalFormat df1 = new DecimalFormat("0.00");
	    		long fileSize = file.getSize();
	            if (fileSize < 1024) {
	                fileSizeString = df1.format((double) fileSize) + "B";
	            } else if (fileSize < 1048576) {
	                fileSizeString = df1.format((double) fileSize / 1024) + "K";
	            } else if (fileSize < 1073741824) {
	                fileSizeString = df1.format((double) fileSize / 1048576) + "M";
	            } else {
	                fileSizeString = df1.format((double) fileSize / 1073741824) + "G";
	            }
		}
	    
	    /**
	     * 获取当前用户
	     * @return
	     */
	    private static String getAuthUser() {
	        SecurityContext context = SecurityContextHolder.getContext();
	        String currentUsername = "";
	        if (context.getAuthentication() != null) {
	            UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
	            currentUsername = userDetails.getUsername();
	        }else{
	            currentUsername = "Rarer";
	        }
	        return currentUsername;

	    }

}
