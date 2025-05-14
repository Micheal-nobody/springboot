package com.example.demo.Service.Form;


import com.example.demo.mapper.OptionsMapper;
import com.example.demo.pojo.Form.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OptionService {

    @Autowired
    private OptionsMapper optionsMapper;

    //更新选项
    public void updateOption(Option option) {
        log.info("updateOption: " + option);

        optionsMapper.updateOption(option);

    }
}
