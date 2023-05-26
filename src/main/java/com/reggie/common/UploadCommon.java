package com.reggie.common;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("common")
public class UploadCommon {

    @Value("${reggie.path}")
    private String path;

    @PostMapping("upload")
    public String upload(MultipartFile file){
        if(file==null){
            throw new CustomExcept("文件未上传");
        }
        String filename = file.getOriginalFilename();
        String suffix;
        try {
            suffix=filename.substring(filename.lastIndexOf("."));
        } catch (Exception e) {
            throw new CustomExcept("文件格式错误");
        }
        filename= UUID.randomUUID()+suffix;

        File dest=new File(path+filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return JSON.toJSONString(R.success(filename));
    }

    @GetMapping("download")
    public void download(String name, HttpServletResponse response){
        File file = new File(path + name);
            String suffix = name.substring(name.lastIndexOf("."));
        response.setContentType("image/" + suffix);
        FileInputStream inputStream = null;
        ServletOutputStream outputStream=null;
        try {
            outputStream = response.getOutputStream();
            inputStream=new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, b);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
