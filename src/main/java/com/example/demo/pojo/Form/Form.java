package com.example.demo.pojo.Form;


import com.example.demo.pojo.ENUM.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    private Long id;
    private String name;
    private Long clubId;
    private String clubName;
    private String createdTime;
    private String updatedTime;
    private Boolean isDeleted;
    private FormStatus status;
    private List<Question> questions;
}

