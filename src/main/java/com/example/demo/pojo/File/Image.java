package com.example.demo.pojo.File;


import com.example.demo.pojo.ENUM.MimeType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Image {
    private Long id;
    private byte[] data;
    private MimeType mimeType;
    private String fileName;
    private String createdTime;
    private Integer width;
    private Integer height;
    private Long size;
//    TODO:更好的json处理方式
    private String metadata;
}
