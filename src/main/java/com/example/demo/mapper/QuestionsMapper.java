package com.example.demo.mapper;


import com.example.demo.pojo.DTO.QuestionDTO;
import com.example.demo.pojo.Form.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO:因为数据库字段变化，需要修改Mapper接口！
@Mapper
public interface QuestionsMapper {

//    @Select("SELECT * FROM questions")
    List<Question> getAllQuestions();

    List<Question> getQuestionsByFormId(Long formId);

    @Select("SELECT id FROM questions WHERE form_id = #{formId}")
    List<Long> getQuestionIdsByFormId(Long id);

//    更新问题
    @Update("UPDATE questions SET form_id = #{formId}, type = #{type}, question_text = #{questionText}, sort_order = #{sortOrder}, is_required = #{isRequired}, is_deleted = #{isDeleted} WHERE id = #{id}")
    int updateQuestion(Question question);

    //使用xml动态SQL更新问题
    void updateQuestionDTO(QuestionDTO questionDTO);



    @Delete("DELETE FROM questions WHERE form_id = #{formId}")
    void deleteQuestionsByFormId(Long formId);



    @Delete("DELETE FROM questions WHERE id = #{id}")
    void deleteQuestionById(Long id);

//    增加问题
    @Insert("INSERT INTO questions (form_id, type, question_text, sort_order, is_required) VALUES (#{formId}, #{type}, #{questionText}, #{sortOrder}, #{isRequired})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addQuestion(Question question);

    @Select("SELECT form_id FROM questions WHERE id = #{id}")
    Long getFormIdByQuestionId(Long id);
}
