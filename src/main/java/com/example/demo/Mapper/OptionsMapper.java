package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.DTO.OptionDTO;
import com.example.demo.pojo.Entity.Form.Option;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO: 因为数据库字段变更，所以需要修改Mapper接口！
@Mapper
public interface OptionsMapper extends BaseMapper<Option> {

//    更新数据
    @Update("UPDATE options SET question_id = #{questionId}, option_text = #{optionText}, sort_order = #{sortOrder} WHERE id = #{id}")
    void updateOption(Option option);

//    新增数据
    @Insert("INSERT INTO options (question_id, option_text, sort_order) VALUES (#{questionId}, #{optionText}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addOption(Option option);

    void updateOptionDTO(OptionDTO optionDTO);

    void updateOptionSortOrders(List<OptionDTO> optionDTOS);

    @Select("SELECT question_id FROM options WHERE id = #{id}")
    Long selectQuestionIDByOptionId(Long id);
}

