package com.example.demo.Service.Form;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.Mapper.OptionsMapper;
import com.example.demo.Mapper.QuestionsMapper;
import com.example.demo.pojo.DTO.QuestionDTO;
import com.example.demo.pojo.Entity.Form.Option;
import com.example.demo.pojo.Entity.Form.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    private QuestionsMapper  questionsMapper;
    @Autowired
    private OptionsMapper optionsMapper;

    //添加问题
    public Question addQuestion(Question question) {
        questionsMapper.addQuestion(question);
        return question;
    }


    //删除问题及选项
    @Transactional
    public void deleteQuestionById(Long id) {
        // 根据QuestionId获取formId
        Long formId = questionsMapper.selectFormIdByQuestionId(id);

        // 软删除删除问题+选项
        questionsMapper.deleteById(id);
        optionsMapper.delete(new LambdaQueryWrapper<Option>().eq(Option::getQuestionId,id));


        // 同步Form中其他问题的sortOrder
//        List<Question> questions = questionsMapper.selectQuestionsByFormId(formId);
        List<Question> questions = questionsMapper.selectList(new LambdaQueryWrapper<Question>().eq(Question::getFormId, formId));
        ArrayList<QuestionDTO> questionDTOS =new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            // 构建questionDTO
            Question question = questions.get(i);
            QuestionDTO questionDTO =new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setSortOrder(i);

            // 添加到questionDTOS
            questionDTOS.add(questionDTO);
        }

        //动态sql更新数据库
        questionsMapper.updateQuestionSortOrder(questionDTOS);
    }


    //软删除表单下的所有问题
    public void softDeleteQuestionsByFormId(Long formId) {
        //根据formId选择所有的QuestionId
        List<Long> questionIds =questionsMapper.selectQuestionIdsByFormId(formId);

        //软删除问题+选项
        questionsMapper.delete(new LambdaQueryWrapper<Question>().eq(Question::getFormId,formId));
        optionsMapper.delete(new LambdaQueryWrapper<Option>().in(Option::getQuestionId,questionIds));
    }



    //更新问题
    public void updateQuestion(QuestionDTO questionDTO) {
        //Mapper层通过动态SQL更新问题
        questionsMapper.updateQuestionDTO(questionDTO);
    }

    //更新问题排序
    public void updateQuestionSortOrder(List<QuestionDTO> questionDTOS) {
        questionsMapper.updateQuestionSortOrder(questionDTOS);
    }


    public List<QuestionDTO> getQuestionsByFormId(Long formId) {
        return questionsMapper.selectQuestionDTOList(new LambdaQueryWrapper<Question>().eq(Question::getFormId,formId));
    }
}
