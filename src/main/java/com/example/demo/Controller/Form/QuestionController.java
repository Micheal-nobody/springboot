package com.example.demo.Controller.Form;


import com.example.demo.Service.Form.FormService;
import com.example.demo.Service.Form.QuestionService;
import com.example.demo.pojo.DTO.QuestionDTO;
import com.example.demo.pojo.Entity.Form.Question;
import com.example.demo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/form/question")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {

    @Autowired
    FormService formService;
    @Autowired
    QuestionService questionService;


    // 根据formId获取Question
    @GetMapping("/getByFormId/{formId}")
    public Result getQuestionsByFormId(@PathVariable Long formId) {
        List<QuestionDTO> questions = questionService.getQuestionsByFormId(formId);
        return Result.success(questions);
    }


    //添加题目
    @PostMapping("/add")
    public Result addQuestion(@RequestBody Question question){
        return Result.success(questionService.addQuestion(question));
    }

    //删除题目
    @DeleteMapping("/delete/{id}")
    public Result deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
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

    //更新问题的sortOrder，数组形式
    @PutMapping("/update/sortOrder")
    public Result updateQuestionSortOrder(@RequestBody List<QuestionDTO> questionDTOS) {

        log.info("update question sort order" + questionDTOS);

        questionService.updateQuestionSortOrder(questionDTOS);

        return Result.success();
    }
}
