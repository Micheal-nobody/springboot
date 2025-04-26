package com.example.demo.pojo.Form;

import com.example.demo.pojo.ENUM.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
        private Long id;                // 主键ID
        private Long formId;            // 表单ID
        private QuestionType type;      // 问题类型
        private String title;           // 问题标题
        private String titleImg;        // 问题图片
        private Integer sortOrder;      // 排序
        private Boolean isRequired;       // 是否必填
        private List<Option> options;   // 选项列表
        private Boolean isDeleted;        // 是否删除
}
