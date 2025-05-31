package com.example.demo.pojo.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String fileName;
    private byte[] fileData;
    private String mimeType;
    private Integer sortOrder;
}
