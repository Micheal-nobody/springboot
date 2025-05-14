package com.example.demo.Service.Form;

import com.example.demo.mapper.QuestionsMapper;
import com.example.demo.pojo.DTO.QuestionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    private QuestionsMapper  questionsMapper;


    //更新问题
    public void updateQuestion(QuestionDTO questionDTO) {
        log.info("update question" + questionDTO);

        //Mapper层通过动态SQL更新问题
        questionsMapper.updateQuestionDTO(questionDTO);
    }
}
