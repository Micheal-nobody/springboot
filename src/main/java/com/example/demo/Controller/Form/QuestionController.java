package com.example.demo.Controller.Form;


import com.example.demo.Service.Form.FormService;
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

    @PostMapping("/add")
    public Result addQuestion(@RequestBody Question question){
        log.info("add question" + question);
        return Result.success(formService.addQuestion(question));
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteQuestion(@PathVariable Long id) {
        log.info("delete question" + id);
        formService.deleteQuestionsById(id);
        return Result.success();
    }
}
