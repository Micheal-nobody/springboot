package com.example.demo.pojo.DTO;


import com.example.demo.pojo.ENUM.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormDTO {
    private Long id;
    private String name;
    private Long clubId;
    private FormStatus status;
}
