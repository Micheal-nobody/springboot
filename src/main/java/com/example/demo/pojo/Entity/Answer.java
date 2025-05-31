package com.example.demo.pojo.Entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("answers")
public class Answer {
    private Long id;
    private Long submitterId;
    private Long questionId;
    private Boolean isDeleted;
}
