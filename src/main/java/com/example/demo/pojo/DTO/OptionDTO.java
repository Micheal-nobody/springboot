package com.example.demo.pojo.DTO;


import com.baomidou.mybatisplus.annotation.TableField;
import com.example.demo.pojo.Entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO {
    private Long id;
    private Long questionId;
    private String optionText;
    private Integer sortOrder;

    @TableField(exist = false)
    private List<File> optionAttachments = new ArrayList<>();   //可能是图片也可能是文件
}
