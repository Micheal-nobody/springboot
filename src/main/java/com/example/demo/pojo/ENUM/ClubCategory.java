package com.example.demo.pojo.ENUM;

import lombok.Getter;

@Getter
public enum ClubCategory {
    SCIENCE("SCIENCE"),
    ARTISTIC("ARTISTIC"),
    SPORTS("SPORTS"),
    VOLUNTEER("VOLUNTEER"),
    OTHERS("OTHERS");

    private final String category;

    // 私有构造函数
    private ClubCategory(String category) {
        this.category = category;
    }
}
