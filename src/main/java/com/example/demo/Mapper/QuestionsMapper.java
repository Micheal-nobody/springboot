package com.example.demo.Mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.DTO.QuestionDTO;
import com.example.demo.pojo.Entity.Form.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO:因为数据库字段变化，需要修改Mapper接口！
@Mapper
public interface QuestionsMapper extends BaseMapper<Question> {

    List<Question> selectQuestionsByFormId(Long formId);

    @Select("SELECT id FROM questions WHERE form_id = #{formId} AND is_deleted = false")
    List<Long> selectQuestionIdsByFormId(Long formId);

    //使用xml动态SQL更新问题
    void updateQuestionDTO(QuestionDTO questionDTO);

//    增加问题
    @Insert("INSERT INTO questions (form_id, type, question_text, sort_order, is_required) VALUES (#{formId}, #{type}, #{questionText}, #{sortOrder}, #{isRequired})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addQuestion(Question question);

    @Select("SELECT form_id FROM questions WHERE id = #{id}")
    Long selectFormIdByQuestionId(Long id);


    //使用动态SQL更新问题排序
    void updateQuestionSortOrder(List<QuestionDTO> questionDTOS);


    //根据权限为club_manager的user_id，查询所有允许访问的QuestionId
    List<Long> selectAllowedQuestionIds(Long userId);


    List<QuestionDTO> selectQuestionDTOList(@Param("ew") Wrapper<Question> wrapper);
}
