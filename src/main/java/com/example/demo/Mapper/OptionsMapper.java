package com.example.demo.Mapper;

import com.example.demo.pojo.Form.Option;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO: 因为数据库字段变更，所以需要修改Mapper接口！
@Mapper
public interface OptionsMapper {

    @Select("SELECT id, url, value, sort_order FROM options")
    public List<Option> getAllOptions();

    @Select("SELECT * FROM options WHERE question_id = #{id} ORDER BY sort_order")
    public List<Option> getOptionByQuestionId(Long id);

//    更新数据
    @Update("UPDATE options SET url = #{url}, value = #{value}, sort_order = #{sortOrder},is_deleted = #{isDeleted} WHERE id = #{id}")
    public void updateOption(Option option);

    @Delete("DELETE FROM options WHERE question_id = #{questionId}")
    void deleteOptionsByQuestionId(Long questionId);

    @Delete("DELETE FROM options WHERE id = #{id}")
    void deleteOptionsById(Long id);

//    新增数据
    @Insert("INSERT INTO options (question_id, option_text, sort_order) VALUES (#{questionId}, #{optionText}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addOption(Option option);

    @Update("UPDATE options SET sort_order = #{sortOrder} WHERE id = #{id}")
    void updateOptionSortOrder(Option option);
}

