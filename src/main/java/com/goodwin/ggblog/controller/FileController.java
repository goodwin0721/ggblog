package com.goodwin.ggblog.controller;

import com.goodwin.ggblog.utils.UploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author goodwin
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "/picture/upload",method = RequestMethod.POST)
    public String uploadPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if(file == null || file.isEmpty()){
            return "null";
        }
        return UploadUtils.uploadImage(file,request);
    }

    @RequestMapping(value = "/picture/remove",method = RequestMethod.POST)
    public boolean removePicture(@RequestParam("path") String path,HttpServletRequest request){
        return UploadUtils.removeFile(path,request);
    }

    @RequestMapping(value = "/avatar/upload",method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if(file == null || file.isEmpty()){
            return "null";
        }
        return UploadUtils.uploadImage(file,request,true);
    }

}
