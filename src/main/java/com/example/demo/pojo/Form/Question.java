package com.example.demo.pojo.Form;

import com.example.demo.pojo.ENUM.QuestionType;
import com.example.demo.pojo.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
        private Long id;
        private Long formId;
        private QuestionType type;
        private String questionText;
        private Integer sortOrder;
        private Boolean isRequired;
        private Boolean isDeleted;
        private List<File> questionAttachments = new ArrayList<>();   //可能是图片也可能是文件
        private List<Option> options = new ArrayList<>();
}
