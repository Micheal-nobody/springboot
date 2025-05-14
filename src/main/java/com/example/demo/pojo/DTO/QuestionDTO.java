package com.example.demo.pojo.DTO;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class QuestionDTO {

    private Long id;
    private String questionText;
    private Integer sortOrder;
    private Boolean isRequired;
}
