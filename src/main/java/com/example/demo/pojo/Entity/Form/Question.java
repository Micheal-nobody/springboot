package com.example.demo.pojo.Entity.Form;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.ENUM.QuestionType;
import com.example.demo.pojo.Entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("questions")
public class Question {
        private Long id;
        private Long formId;
        private QuestionType type;
        private String questionText;
        private Integer sortOrder;
        private Boolean isRequired;
        private Boolean isDeleted;

        @TableField(exist = false)
        private List<File> questionAttachments = new ArrayList<>();   //可能是图片也可能是文件
        @TableField(exist = false)
        private List<Option> options = new ArrayList<>();
}
