package com.example.demo.pojo.ENUM;


import lombok.Getter;

@Getter
public enum FormStatus {
    DRAFT("DRAFT"),
    SUBMITTED("SUBMITTED"),
    PASSED("PASSED"),
    REJECTED("REJECTED");


    private final String status;

    private FormStatus(final String status)
    {
        this.status = status;
    }
}