package com.example.demo.pojo.Form;


import com.example.demo.Serializer.FormSerializer;
import com.example.demo.pojo.ENUM.FormStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonSerialize(using = FormSerializer.class)
public class Form {
    private Long id;
    private String name;
    private Long clubId;
    private String clubName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Boolean isDeleted;
    private FormStatus status;
    //确保从数据库出来后就是空ArrayList，而不是null
    private List<Question> questions = new ArrayList<>();
}


