package com.example.demo.Service.Form;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.Mapper.OptionsMapper;
import com.example.demo.Mapper.QuestionsMapper;
import com.example.demo.pojo.DTO.OptionDTO;
import com.example.demo.pojo.Entity.Form.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OptionService {

    @Autowired
    private OptionsMapper optionsMapper;
    @Autowired
    private QuestionsMapper questionsMapper;


    public Option addOption(Option option) {
        optionsMapper.addOption(option);
        return option;
    }


    @Transactional
    public void deleteOptionById(Long id) {

        //TODO:通过OptionId获取QuestionId
        Long questionId = optionsMapper.selectQuestionIDByOptionId(id);

        //根据id删除选项
        optionsMapper.deleteById(id);
        //根据QuestionId获取选项
        List<Option> options = optionsMapper.selectList(new LambdaQueryWrapper<Option>().eq(Option::getQuestionId, questionId));

        List<OptionDTO> optionDTOS = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            Option option = options.get(i);

            OptionDTO optionDTO = new OptionDTO();
            optionDTO.setId(option.getId());
            optionDTO.setSortOrder(i);

            optionDTOS.add(optionDTO);
        }



        optionsMapper.updateOptionSortOrders(optionDTOS);
    }


    //更新选项
    public void updateOptionDTO(Long id,OptionDTO optionDTO) {
        log.info("updateOption: " + optionDTO);

        optionDTO.setId(id);

        optionsMapper.updateOptionDTO(optionDTO);
    }

    //更新选项排序
    public void updateOptionSortOrders(List<OptionDTO> optionDTOS) {

        optionsMapper.updateOptionSortOrders(optionDTOS);
    }
}
