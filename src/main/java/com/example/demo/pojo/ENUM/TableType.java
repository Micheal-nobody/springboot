package com.example.demo.pojo.ENUM;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableType {
    CLUBS("CLUBS"),
    FORMS("FORMS"),
    QUESTIONS("QUESTIONS"),
    OPTIONS("OPTIONS");

    private final String name;
}
