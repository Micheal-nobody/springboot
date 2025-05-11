package com.example.demo.pojo;


import lombok.Data;

@Data
public class Answer {
    private Long id;
    private Long submitterId;
    private Long questionId;
    private Boolean isDeleted;
}
