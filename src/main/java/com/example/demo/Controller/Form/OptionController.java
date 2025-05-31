package com.example.demo.Controller.Form;


import com.example.demo.Service.Form.OptionService;
import com.example.demo.pojo.DTO.OptionDTO;
import com.example.demo.pojo.Entity.Form.Option;
import com.example.demo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/form/option")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OptionController {

    @Autowired
    OptionService optionService;

    //添加选项
    @PostMapping("/add")
    public Result addOption(@RequestBody Option option) {
        return Result.success(optionService.addOption(option));
    }

    //删除选项
    @DeleteMapping("/delete/{id}")
    public Result deleteOption(@PathVariable Long id) {
        optionService.deleteOptionById(id);
        return Result.success();
    }


    //更新选项
    @PatchMapping("/update/{id}")
    public Result updateOption(@PathVariable Long id,@RequestBody OptionDTO optionDTO) {
        log.info("正在处理patch请求 {}" , optionDTO);

        optionService.updateOptionDTO(id,optionDTO);

        return Result.success();
    }

    //更新sortOrder
    @PutMapping("/update/sortOrder")
    public Result updateOptionSortOrder(@RequestBody List<OptionDTO> optionDTOS) {

        optionService.updateOptionSortOrders(optionDTOS);

        return Result.success();
    }
}
