package com.example.demo.Controller.Form;


import com.example.demo.Service.Form.FormService;
import com.example.demo.Service.Form.QuestionService;
import com.example.demo.pojo.DTO.QuestionDTO;
import com.example.demo.pojo.Form.Question;
import com.example.demo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/form/question")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {

    @Autowired
    FormService formService;
    @Autowired
    QuestionService questionService;

    //添加题目
    @PostMapping("/add")
    public Result addQuestion(@RequestBody Question question){
        return Result.success(formService.addQuestion(question));
    }

    //删除题目
    @DeleteMapping("/delete/{id}")
    public Result deleteQuestion(@PathVariable Long id) {
        formService.deleteQuestionsById(id);
        return Result.success();
    }

    //更新题目
    //TODO:引入DTO？部分更新数据
    @PatchMapping("/update/{id}")
    public Result updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        questionDTO.setId(id);
        questionService.updateQuestion(questionDTO);

        return Result.success();
    }
}
