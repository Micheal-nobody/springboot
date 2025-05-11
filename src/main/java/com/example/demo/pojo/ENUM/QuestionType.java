package com.example.demo.pojo.ENUM;


import lombok.Getter;

@Getter
public enum QuestionType {
    TEXT("TEXT"),
    SINGLE_SELECT("SINGLE_SELECT"),
    MULTI_SELECT("MULTI_SELECT"),
    SCORE("SCORE");

    private final String type;

    private QuestionType(final String type) {
        this.type = type;
    }
}
