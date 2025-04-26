package com.example.demo.pojo;


import lombok.Data;

@Data
public class Answer {
    private Long id;             // 主键ID
    private Long submitterId;    // 关联提交者ID
    private Long questionId;     // 关联问题ID
    private AnswerJson answer;       // 回答内容
}
