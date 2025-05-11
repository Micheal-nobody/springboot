package com.example.demo.Service.Form;


import com.example.demo.mapper.*;
import com.example.demo.pojo.ENUM.FormStatus;
import com.example.demo.pojo.File;
import com.example.demo.pojo.Form.Form;
import com.example.demo.pojo.Form.Option;
import com.example.demo.pojo.Form.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(FormService.class);
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
        form.setCreatedTime(LocalDateTime.now());

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
        // 获取表单id
        Long formId = questionsMapper.getFormIdByQuestionId(id);

        //删除问题及选项
        questionsMapper.deleteQuestionById(id);
        optionsMapper.deleteOptionsByQuestionId(id);

        //同步Form中其他问题的sortOrder
        List<Question> questions = questionsMapper.getQuestionsByFormId(formId);
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            question.setSortOrder(i);
            questionsMapper.updateQuestion(question);
        }
    }

    @Transactional
    public void deleteOptionsById(Long id) {
        // 获取问题id
        Long formId = questionsMapper.getFormIdByQuestionId(id);

        //删除选项
        optionsMapper.deleteOptionsById(id);

        //同步Question中其他选项的sortOrder
        List<Option> options = optionsMapper.getOptionByQuestionId(id);
        for (int i = 0; i < options.size(); i++) {
            Option option = options.get(i);
            option.setSortOrder(i);
            optionsMapper.updateOptionSortOrder(option);
        }
    }

    //删除文件
    @Transactional
    public void deleteFileById(Long id) {

        // 获取文件信息
        File file = fileMapper.getFileById(id);

        //删除文件
        fileMapper.deleteFileById(id);

        //同步Form/Question/Option中文件的sortOrder
        List<File> files = fileMapper.getFilesByRelatedAttr(file.getRelatedId(), file.getRelatedType());
        for (int i = 0; i < files.size(); i++) {
            File f = files.get(i);
            f.setSortOrder(i);
            fileMapper.updateFileSortOrder(f);
        }
    }


//endregion

    //region 改
//    更新表单
    @Transactional
    public void updateForm(Form form) {
//        首先更新表单信息
        form.setUpdatedTime(LocalDateTime.now());
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

    //TODO:操作文件显然需要一个单独的服务，这里暂时不做实现
    @Transactional
    public Long upload(MultipartFile file, String relatedType, Long relatedId, Integer sortOrder) throws IOException {
        File myFile = new File();

        myFile.setFileName(file.getOriginalFilename());
        myFile.setFileData(file.getBytes());
        myFile.setRelatedType(relatedType);
        myFile.setRelatedId(relatedId);
        myFile.setSortOrder(sortOrder);
        myFile.setMimeType(file.getContentType());
        myFile.setCreateTime(LocalDateTime.now());

        try {
            fileMapper.insertFile(myFile);
        }catch (Exception e){
            log.error("upload file error: " + e.getMessage());
            throw e;
        }

        return myFile.getId();
    }


    public void updateStatus(Long id, FormStatus status) {
        formMapper.updateStatus(id, status);
    }
    //endregion

    //region 查
    public List<Form> getByClubId(Long clubId) {

        List<Form> forms = formMapper.getByClubId(clubId);

        return forms;
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

    public Form getFormById(Long id) {
        return formMapper.getFormById(id);
    }

    //endregion



}
