package com.example.demo.pojo.ENUM;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {
    JPG("jpg"),
    PNG("png"),
    GIF("gif"),
    BMP("bmp"),
    WEBP("webp"),
    SVG("svg"),
    ICO("ico"),
    TIFF("tiff"),
    RAW("raw"),
    OTHER("other");

    private final String value;
}
