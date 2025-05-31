package com.example.demo.pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.ENUM.ClubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("clubs")
public class Club {

    private Long id;
    private String name;
    private String description;
    private ClubCategory category;
    private Long leaderId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDeleted;
}
