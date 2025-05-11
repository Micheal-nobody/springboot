package com.example.demo.pojo.ENUM;

import lombok.Getter;

@Getter
public enum MimeType {
    PDF("application/pdf"),
    WORD("application/sword"),
    EXCEL("application/vnd.ms-excel"),
    PPT("application/vnd.ms-powerpoint"),
    JPG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    SVG("image/svg+xml"),
    MP3("audio/mpeg"),
    MP4("video/mp4"),
    AVI("video/avi"),
    MPEG("video/mpeg"),
    OGG("audio/ogg"),
    OGV("video/ogg"),
    WEBM("video/webm"),
    FLV("video/x-flv"),
    WMV("video/x-ms-wmv"),
    MOV("video/quicktime"),
    M4V("video/x-m4v"),
    M3U8("application/x-mpegURL"),
    XML("application/xml"),
    JSON("application/json"),
    TEXT("text/plain"),
    HTML("text/html"),
    CSS("text/css"),
    JS("text/javascript"),
    CSV("text/csv"),
    TSV("text/tab-separated-values"),
    ZIP("application/zip"),
    RAR("application/x-rar-compressed"),
    TAR("application/x-tar"),
    GZ("application/gzip"),
    BZ2("application/x-bzip2"),
    BMP("image/x-ms-bmp"),
    TIFF("image/tiff"),
    EPS("application/postscript"),
    AI("application/postscript"),
    PSD("image/vnd.adobe.photoshop");


    private final String type;

    private MimeType(String type){
        this.type = type;
    }
}
