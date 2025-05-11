package com.example.demo.pojo.ENUM;


import lombok.Getter;

@Getter
public enum TableType {
    CLUBS("CLUBS"),
    FORMS("FORMS"),
    QUESTIONS("QUESTIONS"),
    OPTIONS("OPTIONS");

    private final String name;

    private TableType(String name) {
        this.name = name;
    }
}
