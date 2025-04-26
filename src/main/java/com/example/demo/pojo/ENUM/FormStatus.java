package com.example.demo.pojo.ENUM;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormStatus {
    DRAFT("DRAFT"),
    SUBMITTED("SUBMITTED"),
    PASSED("PASSED"),
    REJECTED("REJECTED");


    private final String status;
}