package com.example.demo.pojo.Entity.Form;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.Entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("options")
public class Option {
    private Long id;
    private Long questionId;
    private String optionText;
    private Integer sortOrder;
    private Boolean isDeleted;

    @TableField(exist = false)
    private List<File> optionAttachments = new ArrayList<>();   //可能是图片也可能是文件
}
