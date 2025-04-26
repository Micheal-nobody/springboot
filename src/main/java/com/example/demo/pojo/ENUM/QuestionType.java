package com.example.demo.pojo.ENUM;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {
    TEXT("TEXT"),
    SINGLE_SELECT("SINGLE_SELECT"),
    MULTI_SELECT("MULTI_SELECT"),
    SCORE("SCORE");

    private final String type;
}
