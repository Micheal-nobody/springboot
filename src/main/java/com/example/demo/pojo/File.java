package com.example.demo.pojo;


import com.example.demo.pojo.ENUM.TableType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class File {
    private Long id;
    private String relatedType;
    private Long relatedId;
    private String fileName;
    private byte[] fileData;
    private String mimeType;
    private Integer sortOrder;
    private LocalDateTime createTime;
}
