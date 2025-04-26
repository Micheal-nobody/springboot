package com.example.demo.pojo.ENUM;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CLubCategory {
    SCIENCE("SCIENCE"),
    ARTISTIC("ARTISTIC"),
    SPORTS("SPORTS"),
    VOLUNTEER("VOLUNTEER"),
    OTHERS("OTHERS");

    private final String category;

}
