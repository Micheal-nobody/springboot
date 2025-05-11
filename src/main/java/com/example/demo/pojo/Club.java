package com.example.demo.pojo;

import com.example.demo.pojo.ENUM.ClubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {

    private Long id;
    private String name;
    private String description;
    private ClubCategory category;
    private Long leaderId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDeleted;
}
