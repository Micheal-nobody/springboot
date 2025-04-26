package com.example.demo.Mapper;


import com.example.demo.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswersMapper {

    @Select("SELECT * FROM answers")
    public List<Answer> getAllAnswers();
}
