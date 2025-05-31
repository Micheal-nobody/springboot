package com.example.demo.pojo.Entity.Form;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.ENUM.FormStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("forms")
//@JsonSerialize(using = FormSerializer.class)
public class Form {

    //标记为id

    private Long id;
    private String name;
    private Long clubId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Boolean isDeleted;
    private FormStatus status;

    @TableField(exist = false)
    private List<Question> questions = new ArrayList<>();
}


