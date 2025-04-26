package com.example.demo.Service.Form;


import com.example.demo.Mapper.*;
import com.example.demo.pojo.ENUM.TableType;
import com.example.demo.pojo.Form.Form;
import com.example.demo.pojo.ENUM.FormStatus;
import com.example.demo.pojo.Image;
import com.example.demo.pojo.Form.Option;
import com.example.demo.pojo.Form.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class FormService {

    @Autowired
    private ClubMapper clubMapper;
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private QuestionsMapper questionsMapper;
    @Autowired
    private OptionsMapper optionsMapper;
    @Autowired
    private FileMapper fileMapper;

    //region 增
    public Form createForm(Long clubId,String name) {
        Form form = new Form();
        form.setClubId(clubId);
        form.setName(name);
        form.setStatus(FormStatus.DRAFT);
        form.setCreatedTime(LocalDateTime.now().toString());

        formMapper.insertForm(form);
        return form;
    }

    public Question addQuestion(Question question) {
        questionsMapper.addQuestion(question);
        return question;
    }

    public Option addOption(Option option) {
        optionsMapper.addOption(option);
        return option;
    }
    //endregion

    //region 删

//    删除表单
    @Transactional
    public void deleteFormById(Long id) {
        formMapper.deleteFormById(id);

        // 删除表单下的所有问题
        List<Long> questionIds =questionsMapper.getQuestionIdsByFormId(id);
        for (Long questionId : questionIds) {
            questionsMapper.deleteQuestionById(questionId);
            optionsMapper.deleteOptionsByQuestionId(questionId);
        }
    }

    @Transactional
    public void deleteQuestionsById(Long id) {
        questionsMapper.deleteQuestionById(id);
        optionsMapper.deleteOptionsByQuestionId(id);
    }

    public void deleteOptionsById(Long id) {
        optionsMapper.deleteOptionsById(id);
    }
//endregion

    //region 改
//    更新表单
    @Transactional
    public void updateForm(Form form) {
//        首先更新表单信息
        form.setUpdatedTime(LocalDateTime.now().toString());
        formMapper.updateForm(form);

        System.out.println("form updated");
//        然后更新表单下的所有问题
        for (Question question : form.getQuestions()) {
            question.setFormId(form.getId());
            questionsMapper.updateQuestion(question);

//            更新选项信息
            for (Option option : question.getOptions()) {
                option.setQuestionId(question.getId());
                optionsMapper.updateOption(option);
            }
        }
    }

    public void upload(MultipartFile file) throws IOException {
        // TODO: 上传文件
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(TableType.FORMS);
        image.setData(file.getBytes());
        image.setCreate_time(LocalDateTime.now().toString());
        fileMapper.insertImage(image);
    }

    public List<Form> getAllForms() {
        return formMapper.getAllForms();
    }

    public void updateStatus(Long id, FormStatus status) {
        formMapper.updateStatus(id, status);
    }
    //endregion

    //region 查
    public List<Form> getByClubId(Long clubId) {
        return formMapper.getByClubId(clubId);
    }

    public List<Form> getFormsByStatus(FormStatus status) {
        return formMapper.getFormByStatus(status);
    }

    public List<Form> getSubmittedForms() {
        return formMapper.getSubmittedForms(FormStatus.DRAFT);
    }

    public List<Form> getFormsByName(String name) {
        return formMapper.getFormsByName(name);
    }

    public List<Form> getFormsByClubName(String clubName) {
        List<Long> clubIds = clubMapper.getClubIdsByName(clubName);

        List<Form> forms = new ArrayList<Form>();
        for (Long clubId : clubIds) {
            forms.addAll(formMapper.getByClubId(clubId));
        }
        return forms;
    }

    //endregion


}
