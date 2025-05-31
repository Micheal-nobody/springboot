package com.example.demo.pojo.DTO;

import com.example.demo.pojo.ENUM.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private Long formId;
    private QuestionType type;
    private String questionText;
    private Integer sortOrder;
    private Boolean isRequired;

    private List<FileDTO> questionAttachments = new ArrayList<>();
    private List<OptionDTO> options = new ArrayList<>();
}
