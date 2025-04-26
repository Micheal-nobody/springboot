package com.example.demo.pojo;

import com.example.demo.pojo.ENUM.CLubCategory;
import com.example.demo.pojo.Form.Form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    private Long id;
    private String name;
    private String description;
    private String coverImage;
    private CLubCategory category;
    private Long leaderId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDeleted;
}
