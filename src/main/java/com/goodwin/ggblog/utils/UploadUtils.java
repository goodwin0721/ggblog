package com.goodwin.ggblog.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 上传图片工具类
 * @author goodwin
 */
public class UploadUtils {

    public static String uploadImage(MultipartFile multipartFile, HttpServletRequest request, boolean isAvatar){

        String realFileName = multipartFile.getOriginalFilename();
        //获得文件后缀
        String imgSuffix = realFileName.substring(realFileName.lastIndexOf("."));
        //存放的相对路径
        String relativePath = "";
        //重新命名文件
        String newFilename = UUID.randomUUID() +imgSuffix;
        String separator = File.separator;
        if(isAvatar){
            relativePath = "upload" + separator + "avatar";
        } else {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
            String datePath = dateFormat.format(new Date());
            relativePath = "upload" + separator + "image" + separator + datePath;
        }
        //上传的文件夹绝对路径
        String savePath = request.getSession().getServletContext().getRealPath("/")+ separator + relativePath;
        try {
            File directory  = new File(savePath);
            if (!directory.exists()){
                directory.mkdirs();
            }
            File targetFilename = new File(directory, newFilename);
            multipartFile.transferTo(targetFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativePath + separator  + newFilename;
    }

    public static String uploadImage(MultipartFile multipartFile, HttpServletRequest request){
        return uploadImage(multipartFile,request,false);
    }

    public static boolean removeFile(String filepath){
        File file = new File(filepath);
        return file.delete();
    }

    public static boolean removeFile(String filepath,HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/") + File.separator + filepath;
        File file = new File(path);
        return file.delete();
    }
}