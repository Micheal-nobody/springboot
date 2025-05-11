package com.example.demo.pojo.Form;

import com.example.demo.pojo.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    private Long id;
    private Long questionId;
    private String optionText;
    private Integer sortOrder;
    private Boolean isDeleted;
    private List<File> optionAttachments = new ArrayList<>();   //可能是图片也可能是文件
}
