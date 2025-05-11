package com.example.demo.Controller;


import com.example.demo.Service.Form.FormService;
import com.example.demo.pojo.Result;
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


    //上传文件
    @PostMapping("/upload")
    public Result uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("relatedType") String relatedType,
            @RequestParam("relatedId") Long relatedId,
            @RequestParam("sortOrder") Integer sortOrder
    ) throws IOException {
        // TODO: 错误处理
        // TODO: 上传文件逻辑

        log.info("接收到请求，图片名为{}", file.getOriginalFilename());


        //TODO：上传成功后，返回图片id，此处为测试
        return Result.success(formService.upload(file, relatedType, relatedId, sortOrder));
    }

    //删除文件
    @DeleteMapping("/delete/{id}")
    public Result deleteImage(@PathVariable("id") Long id) {
        // TODO: 错误处理
        // TODO: 删除文件逻辑

        log.info("接收到请求，删除图片id为{}", id);

        formService.deleteFileById(id);

        //TODO：删除成功后，返回成功信息
        return Result.success();
    }

}
