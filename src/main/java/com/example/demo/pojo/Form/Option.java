package com.example.demo.pojo.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    private Long id;
    private Long questionId;
    private String value;
    private String url;
    private Integer sortOrder;
    private Boolean isDeleted;
}
