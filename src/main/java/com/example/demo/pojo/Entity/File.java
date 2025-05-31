package com.example.demo.pojo.Entity;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName("files")
public class File {
    private Long id;
    private String relatedType;
    private Long relatedId;
    private String fileName;
    private byte[] fileData;
    private String mimeType;
    private Integer sortOrder;
    private LocalDateTime createTime;

    @TableLogic
    private Boolean isDeleted;
}
