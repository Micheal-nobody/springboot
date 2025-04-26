package com.example.demo.Mapper;


import com.example.demo.pojo.Form.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionsMapper {

//    @Select("SELECT * FROM questions")
    public List<Question> getAllQuestions();

    public List<Question> getQuestionsByFormId(Long formId);

//    更新问题
    @Update("UPDATE questions SET title = #{title}, title_img = #{titleImg}, sort_order = #{sortOrder}, is_required = #{isRequired} , is_deleted = #{isDeleted} WHERE id = #{id}")
    public int updateQuestion(Question question);

    @Delete("DELETE FROM questions WHERE form_id = #{formId}")
    void deleteQuestionsByFormId(Long formId);

    @Select("SELECT id FROM questions WHERE form_id = #{formId}")
    List<Long> getQuestionIdsByFormId(Long id);

    @Delete("DELETE FROM questions WHERE id = #{id}")
    void deleteQuestionById(Long id);

//    增加问题
    @Insert("INSERT INTO questions (form_id, type, title, title_img, sort_order, is_required) VALUES (#{formId}, #{type}, #{title}, #{titleImg}, #{sortOrder}, #{isRequired})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addQuestion(Question question);
}
