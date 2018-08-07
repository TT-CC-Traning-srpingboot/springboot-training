package com.hsbc.springboot.springboottraining.controller;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
import com.hsbc.springboot.springboottraining.entity.FolderEntity;
import com.hsbc.springboot.springboottraining.entity.UserEntity;
import com.hsbc.springboot.springboottraining.service.FileService;
import com.hsbc.springboot.springboottraining.service.FolderService;
import com.hsbc.springboot.springboottraining.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 文件上传下载
 * @author Lucy
 * * @version 1.0.0
 */
@Controller
@RequestMapping("/file")
public class FileManagerController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private FolderService folderService;

    private static final Logger log = LoggerFactory.getLogger(FileManagerController.class);

    String filePath = "E:\\IdeaProjects\\springboot-training\\src\\main\\java";

    //跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 file.html
        return "file";
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

            //File localFile = new File(filePath, file_name );
            //检查路径是否存在
            String path = filePath + "\\uploadfile\\" + currentUsername + "\\" + file_name;

            File fileDir = new File(path);
            if (!fileDir.getParentFile().exists()) {// 判断/download目录是否存在
                fileDir.getParentFile().mkdirs();// 创建目录
            }

            file.transferTo(fileDir);// 文件写入
            // 文件大小转换
            DecimalFormat df1 = new DecimalFormat("0.00");
            String fileSizeString = "";
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
            //创建文件夹

            //根据用户名查询用户信息
            UserEntity userEntity = userService.findByUserName(currentUsername);
            //保存文件夹信息
            FolderEntity folderEntity = new FolderEntity();
            folderEntity.setFolderName(currentUsername);
            folderEntity.setCreateUser(currentUsername);
            folderEntity.setCreateDate(new Date());
            folderEntity.setUserId(userEntity.getUserId());
            folderService.insertFolder(folderEntity);

            //保存文件数据到数据库
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file_name);
            fileEntity.setFolderId(folderEntity.getFolderId());
            fileEntity.setFilePath(filePath);
            fileEntity.setFileSize(fileSizeString);
            fileEntity.setFileType(suffixName);
            fileEntity.setUserId(userEntity.getUserId());
            fileEntity.setUploadUser(userEntity.getUserName());
            fileEntity.setUploadDate(new Date());
            fileEntity.setDeleteFlag(0);
            fileService.insertFile(fileEntity);

            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @RequestMapping("/download/{fileID}")
    @ResponseBody
    public String downloadFile(HttpServletRequest request,@PathVariable long fileID, HttpServletResponse res) {
        //根据文件ID查询文件
        FileEntity fileEntity = fileService.findByFileId(fileID);
        //调用获取当前用户
        String currentUsername = getAuthUser();
        //String fileName = "idea.txt";// 文件名
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileEntity.getFileName());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath+"\\uploadfile\\"+currentUsername+"\\"
                    + fileEntity.getFileName())));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
        return "下载成功";
    }




}
