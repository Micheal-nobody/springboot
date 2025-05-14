package com.example.demo.Controller.Form;


import com.example.demo.Service.Form.FormService;
import com.example.demo.Service.Form.OptionService;
import com.example.demo.pojo.Form.Option;
import com.example.demo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/form/option")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OptionController {

    @Autowired
    FormService formService;
    @Autowired
    OptionService optionService;

    //添加选项
    @PostMapping("/add")
    public Result addOption(@RequestBody Option option) {
        return Result.success(formService.addOption(option));
    }

    //删除选项
    @DeleteMapping("/delete/{id}")
    public Result deleteOption(@PathVariable Long id) {
        formService.deleteOptionsById(id);
        return Result.success();
    }

    //更新选项
    @PutMapping("/update")
    public Result updateOption(@RequestBody Option option) {

        optionService.updateOption(option);

        return Result.success();
    }
}
