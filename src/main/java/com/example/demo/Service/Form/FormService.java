package com.example.demo.Service.Form;


import com.example.demo.Mapper.*;
import com.example.demo.pojo.ENUM.FormStatus;
import com.example.demo.pojo.Entity.File;
import com.example.demo.pojo.Entity.Form.Form;
import com.example.demo.pojo.Entity.Form.Option;
import com.example.demo.pojo.Entity.Form.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
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
    @Autowired
    private QuestionService questionService;

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



    //endregion

    //region 删

    //    软删除表单
    @Transactional
    public void deleteFormById(Long id) {
        formMapper.softDeleteFormById(id);

        // 删除表单下的所有问题
        questionService.softDeleteQuestionsByFormId(id);
    }



    //删除文件
    @Transactional
    public void deleteFileById(Long id) {

        // 获取文件信息
        File file = fileMapper.selectById(id);

        //删除文件
        fileMapper.deleteById(id);

        //同步Form/Question/Option中文件的sortOrder
        List<File> files = fileMapper.getFilesByRelatedAttr(file.getRelatedId(), file.getRelatedType());
        for (int i = 0; i < files.size(); i++) {
            File f = files.get(i);
            f.setSortOrder(i);
            //TODO:bro，答应我不要再用这么乐色的方式更新数据了，好吗？好的。
            fileMapper.updateFileSortOrder(f);
        }
    }


//endregion

    //region 改
    //TODO:或者把他们删了，我实在是懒得改我的屎山了，下面的所有代码（所有的方法）都是没有经过优化的，多有很可能有bug
    //TODO:改起来好痛苦，我要去学新东西了，比如AOP输出项目日志，自定义注解，Validation，redis，或者开个新项目体验 mybatis-plus，再见了我的狗屎代码！！！！！
//    更新表单
    @Transactional
    public void updateForm(Form form) {
        //TODO:使用动态SQL一次访问完成更新，而不是像现在这样

//        首先更新表单信息
        form.setUpdatedTime(LocalDateTime.now());
        formMapper.updateForm(form);


//        然后更新表单下的所有问题
        for (Question question : form.getQuestions()) {
            question.setFormId(form.getId());
            questionsMapper.insertOrUpdate(question);

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
            fileMapper.insert(myFile);
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

    public Form getFormById(Long id) {
        return formMapper.getFormById(id);
    }

    //endregion

}
