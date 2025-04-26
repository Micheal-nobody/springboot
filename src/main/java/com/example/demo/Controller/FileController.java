package com.example.demo.Controller;


import com.example.demo.Mapper.FileMapper;
import com.example.demo.pojo.Image;
import com.example.demo.pojo.Result;
import com.example.demo.Service.Form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/file")
//允许所有网页访问
@CrossOrigin(origins = "*")
public class FileController {

    @Autowired
    FormService formService;

    @Autowired
    FileMapper fileMapper;


    @GetMapping("/get/{id}")
    public Result getImageById(@PathVariable Long id) {
        // TODO: 下载文件逻辑
        Image image = fileMapper.getImageById(id);

        return Result.success(image);
    }

    //    接收二进制数据
    @PostMapping("/upload")
    public Result uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // TODO: 错误处理
        // TODO: 上传文件逻辑

        log.info("接收到请求，图片名为"+file.getOriginalFilename());

        formService.upload(file);

        return null;
    }

    @PostMapping("/test")
    public Result setImage( MultipartFile file) {
        // TODO: 错误处理
        System.out.println("接受到文件  "+file.getOriginalFilename());



        return Result.success();
    }
}
