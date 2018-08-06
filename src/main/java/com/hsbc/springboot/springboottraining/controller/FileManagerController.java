package com.hsbc.springboot.springboottraining.controller;

import com.hsbc.springboot.springboottraining.entity.FileEntity;
import com.hsbc.springboot.springboottraining.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger log = LoggerFactory.getLogger(FileManagerController.class);

    String filePath = "E:\\IdeaProjects\\springboot-training\\src\\main\\java\\com\\hsbc\\springboot\\springboottraining\\controller";


    //跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 file.html
        return "file";
    }

    /*private void getAuthUser() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication() != null) {
            authUser = (AuthUser) context.getAuthentication().getPrincipal();
            currentUsername = authUser.getUsername();
        }

    }*/

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }

            // 获取原始文件名
            String origin_Name = file.getOriginalFilename();
            log.info("上传的文件名为：" + origin_Name);
            // 获取文件名
            String file_name = origin_Name.substring(origin_Name.lastIndexOf("\\")+1);
            log.info("文件的后缀名为：" + file_name);
            // 获取文件的后缀名
            String suffixName = origin_Name.substring(origin_Name.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);

            File localFile = new File(filePath, file_name );
            file.transferTo(localFile);// 文件写入
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

            //保存数据到数据库
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file_name);
            fileEntity.setFilePath(filePath);
            fileEntity.setFileSize(fileSizeString);
            fileEntity.setFileType(suffixName);
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
        FileEntity fileEntity = fileService.findByFileId(fileID);
        //String fileName = "idea.txt";// 文件名
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileEntity.getFileName());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath+"\\"
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
