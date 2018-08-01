package com.hsbc.springboot.springboottraining.util;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @Author Leo
* @Description 文件上传下载工具类
* @Date 21:28 2018/8/1
* @since 1.0.0
**/
public class FileUploadUtil {

    /**
    * @Author Leo
    * @Description 根据路径确定目录，没有目录，则创建目录
    * @Date 22:01 2018/8/1
    * @Param path 文件路径
    * @return void
    **/
    private static void createDir(String path) {
        File fileDir = new File(path);
        if (!fileDir.exists() && !fileDir.isDirectory()) {// 判断/download目录是否存在
            fileDir.mkdir();// 创建目录
        }
    }

    /**
    * @Author Leo
    * @Description 将文件名解析成文件的上传路径
    * @Date 23:02 2018/8/1
    * @Param fileName
    * @return 上传到服务器的文件名
    **/
    public static String transPath(String fileName, String path) {
        createDir(path);
        Date date = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddhhmmssSSS");// 定义到毫秒
        String nowStr = dateformat.format(date);
        String filenameStr = fileName.substring(0, fileName.lastIndexOf("."));// 去掉后缀的文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);// 后缀
        // 如果名称不为"",说明该文件存在，否则说明该文件不存在
        if (fileName.trim() != "") {
            path += "\\" + filenameStr + nowStr + "." + suffix;// 定义上传路径
        }
        return path;
    }

    /**
    * @Author Leo
    * @Description 文件下载
    * @Date 23:02 2018/8/1
    * @Param fileName
    * @Param path
    * @return ResponseEntity
    **/
    public static ResponseEntity<byte[]> downloadFile(String fileName, String path) {
        try {
            fileName = new String(fileName.getBytes("GB2312"), "ISO_8859_1");// 避免文件名中文不显示
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        ResponseEntity<byte[]> byteArr = null;
        try {
            byteArr = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArr;
    }


    /**
    * @Author Leo
    * @Description 将输入流中的数据写入字节数组
    * @Date 23:13 2018/8/1
    * @Param 
    * @return 
    **/
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isClose) {
                try {
                    in.close();
                } catch (Exception e2) {
                    System.out.println("关闭流失败");
                }
            }
        }
        return byteArray;
    }
}