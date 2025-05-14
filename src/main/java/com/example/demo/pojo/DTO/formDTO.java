package com.example.demo.pojo.DTO;


import com.example.demo.pojo.ENUM.FormStatus;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class formDTO {

    private Long id;
    private String name;
    private FormStatus status;
}
